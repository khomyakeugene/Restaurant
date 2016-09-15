package com.company.restaurant.service.impl.common;

import com.company.util.common.Util;
import com.company.util.exception.DataIntegrityException;

/**
 * Created by Yevhen on 04.07.2016.
 */
public class Service {
    private static final String PROPERTY_SHOULD_HAVE_POSITIVE_VALUE_MSG = "%s should have positive value!";
    private static final String amountPropertyName = "amount";

    protected void throwDataIntegrityException(String message) {
        throw new DataIntegrityException(message);
    }

    protected void validateFloatPropertyPositiveness(String propertyName, Float property) {
        if (property != null && property <= 0.0) {
            throwDataIntegrityException(Util.capitalize(String.format(PROPERTY_SHOULD_HAVE_POSITIVE_VALUE_MSG,
                    propertyName)));
        }
    }

    protected void validateAmountPositiveness(Float amount) {
        validateFloatPropertyPositiveness(amountPropertyName, amount);
    }
}
