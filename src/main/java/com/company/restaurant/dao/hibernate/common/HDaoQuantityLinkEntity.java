package com.company.restaurant.dao.hibernate.common;

import com.company.restaurant.model.common.LinkObject;

/**
 * Created by Yevhen on 11.06.2016.
 */
public abstract class HDaoQuantityLinkEntity <T extends LinkObject> extends HDaoAmountLinkEntity<T>  {
    private Integer selectCurrentQuantity(int firstId, int secondId) {
        return selectCurrentAmount(firstId, secondId).intValue();
    }

    protected String amountToString(float amount) {
        return String.valueOf(Math.round(amount));
    }

    protected void increaseQuantity(int firstId, int secondId, Integer increasePortion) {
        increaseAmount(firstId, secondId, new Float(increasePortion));
    }

    protected void decreaseQuantity(int firstId, int secondId, Integer decreasePortion) {
        decreaseAmount(firstId, secondId, new Float(decreasePortion));
    }
}
