package com.company.util.exception;

/**
 * Created by Yevhen on 22.05.2016.
 */
public class DataIntegrityException extends RuntimeException {
    public DataIntegrityException(String reason) {
        super(reason);
    }
}
