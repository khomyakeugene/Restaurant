package com.company.restaurant.dao.common;

import com.company.restaurant.dao.common.resource.Constraints;
import com.company.util.exception.DataIntegrityException;

/**
 * Created by Yevhen on 05.09.2016.
 */
public class ErrorHandlingService {
    public static String getErrorMessage(Exception e) {
        String result = (e instanceof DataIntegrityException) ? e.getMessage() :
                Constraints.transferExceptionToMessage(e);
        if (result == null) {
            result = e.getMessage();
        }

        return result;
    }
}
