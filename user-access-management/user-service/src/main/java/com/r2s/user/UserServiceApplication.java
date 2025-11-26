package com.r2s.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Hello world!
 *
 */

@SpringBootApplication(scanBasePackages = {"com.r2s.user", "com.r2s.core"})   // Scan bean từ core (CustomDetailsService, JwtUtil,...)
@EntityScan(basePackages = "com.r2s.core.entity")
@EnableJpaRepositories(basePackages = "com.r2s.core.repository") // Scan repository từ core
public class UserServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
