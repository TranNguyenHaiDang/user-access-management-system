package com.r2s.user.exception;

import lombok.Getter;

/**
 * AppException
 * <p>
 * Provides business logic for managing employment details.
 * <p>
 * Version 1.0
 * Date: 20/11/2025
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE         AUTHOR       DESCRIPTION
 * -------------------------------------
 * 20/11/2025    HDang      Create
 */
@Getter
public class BusinessException extends RuntimeException {
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    private final ErrorCode errorCode;
}
