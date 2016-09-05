package com.company.restaurant.model;

import com.company.restaurant.model.common.SimpleDic;

import java.io.Serializable;

/**
 * Created by Yevhen on 28.06.2016.
 */
public class PortionType extends SimpleDic implements Serializable {
    public int getPortionTypeId() {
        return getId();
    }

    public void setPortionTypeId(int portionTypeId) {
        setId(portionTypeId);
    }
}
