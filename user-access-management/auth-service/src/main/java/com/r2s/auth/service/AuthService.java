package com.r2s.auth.service;

import com.r2s.auth.dto.request.LoginRequest;
import com.r2s.auth.dto.request.RegisterRequest;
import com.r2s.auth.dto.response.AuthResponse;
import com.r2s.auth.entity.Role;
import com.r2s.auth.entity.User;
import com.r2s.auth.enums.UserStatus;
import com.r2s.auth.exception.BusinessException;
import com.r2s.auth.exception.ErrorCode;
import com.r2s.auth.repository.UserRepository;
import com.r2s.auth.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AuthServiceImpl
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
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void register(RegisterRequest request) {
        if (!request.getPassword().equals(request.getConfirmPasword()))
            throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
        User user = User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(UserStatus.ACTIVE)
                .role(Role.ROLE_USER)
                .build();
        try {
            userRepository.save(user);
        }catch(DataIntegrityViolationException e) {
            throw new BusinessException(ErrorCode.USER_EXISTED);
        }
    }
    public AuthResponse login(LoginRequest request) {
        log.info("Attempting login for user: {}", request.getEmail());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authenticated = authenticationManager.authenticate(authenticationToken);
        User user = (User) authenticated.getPrincipal();//Thông tin user đang đăng nhập là kiểu object nên phải ép về user
        return AuthResponse.builder()
                .accessToken(jwtUtil.generateToken(user, false))
                .refreshToken(jwtUtil.generateToken(user, true))
                .build();
    }
}
