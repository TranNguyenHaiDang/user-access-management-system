package com.r2s.auth.controller;

import com.r2s.auth.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RoleController
 * <p>
 * Provides business logic for managing employment details.
 * <p>
 * Version 1.0
 * Date: 26/11/2025
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE         AUTHOR       DESCRIPTION
 * -------------------------------------
 * 26/11/2025    HDang      Create
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ApiResponse<String> userAccess() {
        return ApiResponse.success(null, "Hello USER");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<String> adminAccess() {
        return ApiResponse.success(null, "Hello ADMIN");
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public ApiResponse<String> moderatorAccess() {
        return ApiResponse.success(null, "Hello MODERATOR");
    }
}
