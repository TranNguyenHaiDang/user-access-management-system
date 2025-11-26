package com.r2s.user.config;

import com.r2s.user.exception.BusinessException;
import com.r2s.user.exception.ErrorCode;
import com.r2s.user.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Objects;

/**
 * JwtDecoderConfiguration
 * <p>
 * Provides business logic for managing employment details.
 * <p>
 * Version 1.0
 * Date: 09/10/2025
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE         AUTHOR       DESCRIPTION
 * -------------------------------------
 * 09/10/2025   Hải Đăng      Create
 */
@Component
@RequiredArgsConstructor
public class JwtDecoderConfig implements JwtDecoder {

    @Value("${jwt.secret-key}")
    private String secretKey;

    private final JwtUtil jwtUtil;
    private NimbusJwtDecoder nimbusJwtDecoder = null;

    @Override
    public Jwt decode(String token) throws JwtException {
        if(!jwtUtil.verifyToken(token)){
            throw new BusinessException(ErrorCode.TOKEN_INVALID);
        }
        if(Objects.isNull(nimbusJwtDecoder)){
            SecretKey secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HS512");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }
        return nimbusJwtDecoder.decode(token);
    }
    //verifyToken() = bảo vệ đầu vào (token có hợp lệ không).
    //decode() = phân tích nội dung (để framework dùng token đó xác thực và cấp quyền truy cập API).
}
