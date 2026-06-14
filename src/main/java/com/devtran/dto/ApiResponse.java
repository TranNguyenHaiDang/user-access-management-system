package com.devtran.dto;

import com.devtran.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ApiResponse
 * <p>
 * Provides business logic for managing employment details.
 * <p>
 * Version 1.0
 * Date: 17/11/2025
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE         AUTHOR       DESCRIPTION
 * -------------------------------------
 * 17/11/2025    HDang      Create
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    int code;

    String message;
    T result;

    // Success response
    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .code(1000)
                .message(message)
                .result(data)
                .build();
    }

    // Error response từ ErrorCode
    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
            return ApiResponse.<T>builder()
                    .code(errorCode.getCode())
                    .message(errorCode.getMessage())
                .build();
    }
}
