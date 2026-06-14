package com.devtran.service;

import com.devtran.dto.request.UpdateUserRequest;
import com.devtran.dto.response.UserResponse;
import com.devtran.entity.User;
import com.devtran.enums.UserStatus;
import com.devtran.exception.BusinessException;
import com.devtran.exception.ErrorCode;
import com.devtran.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserServiceImpl
 * <p>
 * Provides business logic for managing employment details.
 * <p>
 * Version 1.0
 * Date: 22/11/2025
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE         AUTHOR       DESCRIPTION
 * -------------------------------------
 * 22/11/2025    HDang      Create
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> getAllUsers() {
        log.debug("Retrieving all users");
        return userRepository.findAll().stream()
                .map(this::toUserResponse)   // ← đẹp hơn
                .toList();
    }

    public UserResponse getUserById(Long userId) {
        log.debug("Retrieving user with id: {}", userId);
        return userRepository.findById(userId)
                .map(this::toUserResponse)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXISTED));
    }

    @Transactional
    public void deleteUser(Long userId) {
        log.debug("Soft deleting user with ID: {}", userId);
        var user = userRepository.findById(userId).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXISTED));
        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);
    }

    @Transactional
    public UserResponse updateUser(String username, UpdateUserRequest request) {
        log.debug("Updating user with username: {}", username);
        var user = userRepository.findByEmail(username).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXISTED));
        if (request.getFullName() != null) {
            user.setFullName(request.getFullName());
        }
        return this.toUserResponse(userRepository.save(user));
    }

    public UserResponse getUserByUsername(String username) {
        log.debug("Retrieving user with username: {}", username);
        return userRepository.findByEmail(username)
                .map(this::toUserResponse)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXISTED));
    }

    private UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .role(user.getRole() != null ? user.getRole().name() : null)
                .fullName(user.getFullName())
                .build();
    }
}
