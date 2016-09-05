package com.company.restaurant.model;

import com.company.restaurant.model.common.SimpleTypeDic;

/**
 * Created by Yevhen on 25.06.2016.
 */
public class State extends SimpleTypeDic {
    public String getStateType() {
        return getType();
    }

    public void setStateType(String stateType) {
        setType(stateType);
    }

    @Override
    public String toString() {
        return "State{" +
                super.toString() +
                "}";
    }
}
