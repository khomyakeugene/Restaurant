package com.company.restaurant.dao;

import com.company.restaurant.model.State;

/**
 * Created by Yevhen on 25.06.2016.
 */
public interface StateDao {
    State findStateByType(String type);
}
