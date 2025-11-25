package com.r2s.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * LoginRequest
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
@Getter
public class LoginRequest {

    @NotBlank(message = "EMAIL_REQUIRED")
    @Email(message = "EMAIL_INVALID")
    private String email;

    @NotBlank(message = "PASSWORD_REQUIRED")
    @Size(min = 6, message = "PASSWORD_INVALID")
    private String password;
}
