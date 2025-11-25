package com.r2s.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * AuthResponse
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
@Setter
@Builder
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
}
