package com.r2s.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Hello world!
 *
 */

@SpringBootApplication(scanBasePackages = {"com.r2s.auth", "com.r2s.core"})   // Scan bean từ core (CustomDetailsService, JwtUtil,...)
@EntityScan(basePackages = "com.r2s.core.entity")
@EnableJpaRepositories(basePackages = "com.r2s.core.repository") // Scan repository từ core
public class AuthServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}
