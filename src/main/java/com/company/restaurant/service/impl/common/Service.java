package com.company.restaurant.service.impl.common;

import com.company.util.common.Util;
import com.company.util.exception.DataIntegrityException;

/**
 * Created by Yevhen on 04.07.2016.
 */
public class Service {
    private static final String PROPERTY_SHOULD_HAVE_POSITIVE_VALUE_MSG = "%s should have positive value!";
    private static final String PROPERTY_CANNOT_BE_EMPTY_MSG = "%s cannot be empty!";
    private static final String AMOUNT_PROPERTY_NAME = "amount";

    protected static final String PLEASE_SPECIFY_AN_INGREDIENT_MSG = "Please, specify an ingredient";
    protected static final String PLEASE_SPECIFY_A_PORTION_MSG = "Please, specify a portion";

    protected void throwDataIntegrityException(String message) {
        throw new DataIntegrityException(message);
    }

    private void throwPropertyCannotBeEmptyMsg(String propertyName) {
        throwDataIntegrityException(Util.capitalize(String.format(PROPERTY_CANNOT_BE_EMPTY_MSG,
                propertyName)));
    }

    protected void validateNotNullProperty(String propertyName, String property) {
        if (property == null || property.trim().isEmpty()) {
            throwPropertyCannotBeEmptyMsg(propertyName);
        }
    }

    protected void validateFloatPropertyPositiveness(String propertyName, Float property) {
        if (property != null && property <= 0.0) {
            throwDataIntegrityException(Util.capitalize(String.format(PROPERTY_SHOULD_HAVE_POSITIVE_VALUE_MSG,
                    propertyName)));
        }
    }

    private void validateNotNullProperty(String propertyName, Float property) {
        if (property == null) {
            throwPropertyCannotBeEmptyMsg(propertyName);
        }
    }

    protected void validateNotNullFloatPropertyPositiveness(String propertyName, Float property) {
        validateNotNullProperty(propertyName, property);
        validateFloatPropertyPositiveness(propertyName, property);
    }

    protected void validateAmountPositiveness(Float amount) {
        validateFloatPropertyPositiveness(AMOUNT_PROPERTY_NAME, amount);
    }

    protected void validateNotNullAmountPositiveness(Float amount) {
        validateNotNullFloatPropertyPositiveness(AMOUNT_PROPERTY_NAME, amount);
    }
}
