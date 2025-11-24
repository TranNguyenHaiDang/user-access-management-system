package com.r2s.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 * <p>
 * Provides business logic for managing employment details.
 * <p>
 * Version 1.0
 * Date: 24/11/2025
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE         AUTHOR       DESCRIPTION
 * -------------------------------------
 * 24/11/2025    HDang      Create
 */
@RestController
@RequestMapping("/api/users")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from User Service";
    }
}
