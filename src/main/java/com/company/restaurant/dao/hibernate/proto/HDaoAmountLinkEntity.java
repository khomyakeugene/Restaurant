package com.company.restaurant.dao.hibernate.proto;

import com.company.restaurant.model.proto.LinkObject;

/**
 * Created by Yevhen on 11.06.2016.
 */
public abstract class HDaoAmountLinkEntity<T extends LinkObject> extends HDaoLinkEntity<T> {

    protected Float selectCurrentAmount(int firstId, int secondId) {
        String linkData = selectLinkData(firstId, secondId);

        return (linkData  == null) ? null : Float.parseFloat(linkData);
    }

    protected String amountToString(float amount) {
        return String.valueOf(amount);
    }

    protected void increaseAmount(int firstId, int secondId, float increasePortion) {
        Float currentAmount = selectCurrentAmount(firstId, secondId);
        if (currentAmount == null) {
            if (increasePortion > 0.0) {
                save(firstId, secondId, amountToString(increasePortion));
            }
        } else {
            currentAmount += increasePortion;
            if (currentAmount > 0.0) {
                saveOrUpdate(firstId, secondId, amountToString(currentAmount));
            } else {
                delete(firstId, secondId);
            }
        }
    }

    protected void decreaseAmount(int firstId, int secondId, float decreasePortion) {
        increaseAmount(firstId, secondId, -decreasePortion);
    }
}
