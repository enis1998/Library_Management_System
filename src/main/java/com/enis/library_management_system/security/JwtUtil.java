package com.enis.library_management_system.security;

import com.enis.library_management_system.entity.JwtKey;
import com.enis.library_management_system.repository.JwtKeyRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtil {

    private final JwtKeyRepository jwtKeyRepository;

    public JwtUtil(JwtKeyRepository jwtKeyRepository) {
        this.jwtKeyRepository = jwtKeyRepository;
    }

    private Optional<JwtKey> getValidJwtKey() {
        return jwtKeyRepository.findFirstByExpiresAtAfterOrderByCreatedAtDesc(java.time.LocalDateTime.now());
    }

    private Key getSigningKey() {
        String secret = getValidJwtKey()
                .orElseThrow(() -> new RuntimeException("Geçerli bir JWT Key bulunamadı!"))
                .getSecretKey();
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username, long expirationMs) {
        Key key = getSigningKey();

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = parseClaims(token);
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims parseClaims(String token) {
        Key key = getSigningKey();
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}