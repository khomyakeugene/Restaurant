package com.company.restaurant.service.impl;

import com.company.restaurant.dao.StateGraphDao;
import com.company.restaurant.model.StateGraph;
import com.company.util.exception.DataIntegrityException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Created by Yevhen on 22.05.2016.
 */
public class StateGraphRules {
    private static final String IMPOSSIBLE_TO_DETERMINE_CREATION_STATE_PATTERN =
            "It is impossible to determine <creation state> for entity <%s>!";
    private static final String IMPOSSIBLE_TO_DETERMINE_FINITE_STATE_PATTERN =
            "It is impossible to determine <finite state> of entity <%s> for <init state> = <%s> and operation <%s>!";

    private static final String STATE_TYPE_OPEN = "A";
    private static final String STATE_TYPE_CLOSED = "B";
    private static final String ACTION_TYPE_CREATION = "A";
    private static final String ACTION_TYPE_REMOVAL = "B";
    private static final String ACTION_TYPE_FILLING = "C";
    private static final String ACTION_TYPE_CLOSING = "D";
    private static final String ACTION_TYPE_VIEWING = "E";

    private StateGraphDao stateGraphDao;
    private HashMap<String, List<StateGraph>> entityStateGraphMap = new HashMap<>();

    public void setStateGraphDao(StateGraphDao stateGraphDao) {
        this.stateGraphDao = stateGraphDao;
    }

    private void errorMessage(String message) {
        throw new DataIntegrityException(message);
    }

    private List<StateGraph> entityGraphStateList(String entityName) {
        List<StateGraph> result = entityStateGraphMap.get(entityName);
        if (result == null) {
            result = stateGraphDao.findEntityStateGraph(entityName);
            entityStateGraphMap.put(entityName, result);
        }

        return result;
    }

    public String creationState(String entityName) {
        String result = null;

        Optional<StateGraph> optionalStateGraph = entityGraphStateList(entityName).stream().
                filter(s -> (s.getActionType().equals(ACTION_TYPE_CREATION)) &&
                        (s.getInitStateType().equals(s.getFiniteStateType()))).findFirst();
        if (optionalStateGraph.isPresent()) {
            result = optionalStateGraph.get().getInitStateType();
        } else {
            errorMessage(String.format(IMPOSSIBLE_TO_DETERMINE_CREATION_STATE_PATTERN, entityName));
        }

        return result;
    }

    private String finiteState(String entityName, String currentState, String actionType) {
        String result = null;

        String initState = (currentState == null) ? creationState(entityName) : currentState;
        Optional<StateGraph> optionalStateGraph = entityGraphStateList(entityName).stream().
                filter(s -> (s.getActionType().equals(actionType)) &&
                        (s.getInitStateType().equals(initState))).findFirst();
        if (optionalStateGraph.isPresent()) {
            result = optionalStateGraph.get().getFiniteStateType();
        } else {
            errorMessage(String.format(IMPOSSIBLE_TO_DETERMINE_FINITE_STATE_PATTERN, entityName, initState, actionType));
        }

        return result;
    }

    public String closedState(String entityName, String currentState) {
        return finiteState(entityName, currentState, ACTION_TYPE_CLOSING);
    }

    public String deletedState(String entityName, String currentState) {
        return finiteState(entityName, currentState, ACTION_TYPE_REMOVAL);
    }

    private boolean isActionEnabled(String entityName, String currentState, String actionType) {
        boolean result;
        try {
            result = finiteState(entityName, currentState, actionType) != null;
        } catch (DataIntegrityException e) {
            result = false;
        }

        return result;
    }

    public boolean isFillingActionEnabled(String entityName, String currentState) {
        return isActionEnabled(entityName, currentState, ACTION_TYPE_FILLING);
    }
}
