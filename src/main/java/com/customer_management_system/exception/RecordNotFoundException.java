package com.customer_management_system.exception;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
public class RecordNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6638849627494498004L;

    public RecordNotFoundException(String message) {
        super(message);
    }
}

