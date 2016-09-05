package com.company.restaurant.service.impl.common;

import com.company.util.exception.DataIntegrityException;

/**
 * Created by Yevhen on 04.07.2016.
 */
public class Service {
    protected void throwDataIntegrityException(String message) {
        throw new DataIntegrityException(message);
    }
}
