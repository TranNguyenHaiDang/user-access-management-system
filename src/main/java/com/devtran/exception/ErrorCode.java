package com.devtran.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * ErrorCode
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
@AllArgsConstructor
public enum ErrorCode {

    // ====== COMMON (10xx) ======
    INVALID_KEY(1001, "Invalid key", HttpStatus.BAD_REQUEST),

    // ====== AUTHENTICATION / AUTHORIZATION (20xx) ======
    UNAUTHENTICATED(2001, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(2002, "You do not have permission", HttpStatus.FORBIDDEN),
    TOKEN_GENERATION_FAILED(2003, "Token generation failed", HttpStatus.INTERNAL_SERVER_ERROR),
    TOKEN_INVALID(2004, "Token is invalid", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED(2005, "Token has expired", HttpStatus.UNAUTHORIZED),
    TOKEN_REVOKED(2006, "Token has been revoked", HttpStatus.UNAUTHORIZED),

    // ====== USER (30xx) ======
    USER_EXISTED(3001, "User already exists", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(3002, "User not found", HttpStatus.NOT_FOUND),
    FULLNAME_REQUIRED(3003, "Full name is required", HttpStatus.BAD_REQUEST),
    PASSWORD_REQUIRED(3004, "Password is required", HttpStatus.BAD_REQUEST),
    PASSWORD_MISMATCH(3005, "Password and Confirm Password do not match", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(3006, "Password must be at least 6 characters", HttpStatus.BAD_REQUEST),
    CONFIRM_PASSWORD_REQUIRED(3007, "Confirm password is required", HttpStatus.BAD_REQUEST),
    EMAIL_REQUIRED(3008, "Email is required", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(3009, "Email format is invalid", HttpStatus.BAD_REQUEST),
    ;


    private final int code;
    private final String message;
    private final HttpStatus statusCode;

}
