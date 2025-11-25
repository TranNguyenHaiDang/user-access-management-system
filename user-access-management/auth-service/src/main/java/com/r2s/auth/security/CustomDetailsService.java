package com.r2s.auth.security;


import com.r2s.auth.entity.User;
import com.r2s.auth.exception.BusinessException;
import com.r2s.auth.exception.ErrorCode;
import com.r2s.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CustomDetailsService
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
@Service
@RequiredArgsConstructor
public class CustomDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return userRepository.findByEmail(username)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXISTED));
    }
}
