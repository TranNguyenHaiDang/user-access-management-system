package com.r2s.user.controller;

import com.r2s.user.dto.ApiResponse;
import com.r2s.user.dto.request.UpdateUserRequest;
import com.r2s.user.dto.response.UserResponse;
import com.r2s.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController
 * <p>
 * Provides business logic for managing employment details.
 * <p>
 * Version 1.0
 * Date: 19/11/2025
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE         AUTHOR       DESCRIPTION
 * -------------------------------------
 * 19/11/2025    HDang      Create
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    ApiResponse<List<UserResponse>> getAllUsers() {
        log.info("Retrieving all users");
        return ApiResponse.success(userService.getAllUsers(),
                "Users retrieved successfully");
    }

    @GetMapping("/{userId}")
    @PostAuthorize("returnObject.result.email == authentication.name")
    ApiResponse<UserResponse> getUserById(@PathVariable("userId") Long userId) {
        log.info("Retrieving user with id {}", userId);
        return ApiResponse.success(userService.getUserById(userId),
                "User retrieved successfully");
    }

    @GetMapping("/me")
    public ApiResponse<UserResponse> getMyProfile(Authentication authentication) {
        log.info("Retrieving profile for user: {}", authentication.getName());
        return ApiResponse.success(userService.getUserByUsername(authentication.getName()),
                "Profile retrieved successfully");
    }

    @PutMapping("/me")
    ApiResponse<UserResponse> updateMyProfile(@RequestBody UpdateUserRequest request, Authentication authentication) {
        log.info("Updating profile for user: {}", authentication.getName());
        return ApiResponse.success(userService.updateUser(authentication.getName(), request),
                "Profile updated successfully");
    }
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    ApiResponse<String> deleteUser(@PathVariable("userId") Long userId) {
        log.info("Deleting user: {}", userId);
        userService.deleteUser(userId);
        return ApiResponse.success(null, "User deleted successfully");
    }
}
