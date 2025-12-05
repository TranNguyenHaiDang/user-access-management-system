package com.devtran.security;

import com.devtran.entity.User;
import com.devtran.enums.TokenType;
import com.devtran.exception.BusinessException;
import com.devtran.exception.ErrorCode;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * JwtUtil
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
@Component
public class JwtUtil {

    @NonFinal
    @Value("${jwt.secret-key}")
    private String secretKey;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long validDuration;
    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long refreshableDuration;

    public String generateToken(User user, boolean isRefreshToken) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        Date expiredTime = isRefreshToken
                ? new Date(Instant.now().plus(refreshableDuration, ChronoUnit.SECONDS).toEpochMilli())
                : new Date(Instant.now().plus(validDuration, ChronoUnit.SECONDS).toEpochMilli());

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issueTime(new Date(System.currentTimeMillis()))
                .expirationTime(expiredTime)
                .jwtID(UUID.randomUUID().toString())
                .claim("type", isRefreshToken ? TokenType.REFRESH_TOKEN : TokenType.ACCESS_TOKEN)
                .claim("roles", buildScope(user))
                .build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(secretKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new BusinessException(ErrorCode.TOKEN_GENERATION_FAILED);
        }
    }
    public boolean verifyToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);

            // 1. Verify signature
            if (!signedJWT.verify(new MACVerifier(secretKey))) {
                throw new BusinessException(ErrorCode.TOKEN_INVALID);
            }

            // 2. Check expiration
            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (expirationTime.before(new Date())) {
                throw new BusinessException(ErrorCode.TOKEN_EXPIRED);
            }
            return true;
        } catch (ParseException | JOSEException e) {
            throw new BusinessException(ErrorCode.TOKEN_INVALID);
        }
    }
    private String buildScope(User user) {
        StringJoiner joiner = new StringJoiner(" ");
        for (GrantedAuthority authority : user.getAuthorities()) {
            joiner.add(authority.getAuthority());
        }
        return joiner.toString();
    }
}
