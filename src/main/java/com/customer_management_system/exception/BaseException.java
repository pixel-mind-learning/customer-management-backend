package com.customer_management_system.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Getter
@Setter
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -5813841768213591498L;

    private final int errorCode;

    private final String errorDescription;

    public BaseException(int errorCode, Throwable cause, String errorDescription) {
        super(cause);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public BaseException(int errorCode, String errorDescription) {
        super(errorDescription);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public BaseException(int errorCode, String errorDescription, Throwable cause) {
        super(errorDescription, cause);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }
}
