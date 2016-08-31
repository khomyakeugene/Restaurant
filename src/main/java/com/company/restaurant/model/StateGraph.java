package com.company.restaurant.model;

import java.io.Serializable;

/**
 * Created by Yevhen on 22.05.2016.
 */
public class StateGraph implements Serializable {
    private String initStateType;
    private String finiteStateType;
    private String actionType;
    private String entityName;
    private String comment;

    public String getInitStateType() {
        return initStateType;
    }

    public void setInitStateType(String initStateType) {
        this.initStateType = initStateType;
    }

    public String getFiniteStateType() {
        return finiteStateType;
    }

    public void setFiniteStateType(String finiteStateType) {
        this.finiteStateType = finiteStateType;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StateGraph)) return false;

        StateGraph that = (StateGraph) o;

        return initStateType != null ? initStateType.equals(that.initStateType) :
                that.initStateType == null && (finiteStateType != null ?
                        finiteStateType.equals(that.finiteStateType) :
                        that.finiteStateType == null && (actionType != null ?
                                actionType.equals(that.actionType) : that.actionType == null &&
                                (entityName != null ? entityName.equals(that.entityName) :
                                        that.entityName == null && (comment != null ?
                                                comment.equals(that.comment) : that.comment == null))));

    }

    @Override
    public int hashCode() {
        int result = initStateType != null ? initStateType.hashCode() : 0;
        result = 31 * result + (finiteStateType != null ? finiteStateType.hashCode() : 0);
        result = 31 * result + (actionType != null ? actionType.hashCode() : 0);
        result = 31 * result + (entityName != null ? entityName.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StateGraph{" +
                "initStateType='" + initStateType + '\'' +
                ", finiteStateType='" + finiteStateType + '\'' +
                ", actionType='" + actionType + '\'' +
                ", entityName='" + entityName + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
