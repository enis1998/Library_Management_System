package com.enis.library_management_system.service.Jwt;

import com.enis.library_management_system.entity.JwtKey;
import com.enis.library_management_system.repository.JwtKeyRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class JwtKeyServiceImpl implements JwtKeyService {

    private final JwtKeyRepository jwtKeyRepository;

    public JwtKeyServiceImpl(JwtKeyRepository jwtKeyRepository) {
        this.jwtKeyRepository = jwtKeyRepository;
    }

    @PostConstruct
    public void init() {
        boolean hasActiveKey = jwtKeyRepository.findFirstByExpiresAtAfterOrderByCreatedAtDesc(LocalDateTime.now()).isPresent();
        if (!hasActiveKey) {
            generateAndSaveNewKey();
        }
    }

    public JwtKey generateAndSaveNewKey() {
        String newKey = generateSecretKey();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiry = now.plusDays(7);

        JwtKey jwtKey = new JwtKey(newKey, now, expiry);
        return jwtKeyRepository.save(jwtKey);
    }

    public String generateSecretKey() {
        byte[] keyBytes = new byte[64]; // 512-bit key
        new SecureRandom().nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    public JwtKey getActiveKey() {
        return jwtKeyRepository.findFirstByExpiresAtAfterOrderByCreatedAtDesc(LocalDateTime.now())
                .orElseGet(this::generateAndSaveNewKey);
    }
}

