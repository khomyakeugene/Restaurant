package com.company.restaurant.dao.common;

import com.company.restaurant.dao.common.resource.Constraints;

/**
 * Created by Yevhen on 05.09.2016.
 */
public class ErrorHandlingService {
    public static String getErrorMessage(Exception e) {
        String result = Constraints.transferExceptionToMessage(e);

        if (result == null) {
            result = e.getMessage();
        }

        return result;
    }
}
