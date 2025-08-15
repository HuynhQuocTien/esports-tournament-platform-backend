package com.example.esports_tournament_platform_backend.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret; // Khóa bí mật để ký JWT

    @Value("${jwt.expiration}")
    private long expirationMs; // Thời gian sống của token (ms)

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Tạo JWT token
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // ai là chủ sở hữu token
                .setIssuedAt(new Date()) // thời gian tạo
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs)) // thời gian hết hạn
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // ký bằng khóa bí mật
                .compact();
    }

    /**
     * Lấy username từ token
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Kiểm tra token hợp lệ
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
