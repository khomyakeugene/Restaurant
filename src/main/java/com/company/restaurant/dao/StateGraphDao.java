package com.company.restaurant.dao;

import com.company.restaurant.model.StateGraph;

import java.util.List;

/**
 * Created by Yevhen on 22.05.2016.
 */
public interface StateGraphDao {
    List<StateGraph> findEntityStateGraph(String entityName);
}
