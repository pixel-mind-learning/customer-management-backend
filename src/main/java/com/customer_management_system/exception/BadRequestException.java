package com.customer_management_system.exception;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
