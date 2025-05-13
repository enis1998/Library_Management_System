package com.enis.library_management_system.config;

import com.enis.library_management_system.entity.JwtKey;
import com.enis.library_management_system.service.Jwt.JwtKeyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class JwtKeyScheduler {

    private final JwtKeyService jwtKeyService;

    public JwtKeyScheduler(JwtKeyService jwtKeyService) {
        this.jwtKeyService = jwtKeyService;
    }

    // Her gün 00:00'da kontrol eder, süresi geçmişse yeniler
    @Scheduled(cron = "0 0 0 * * ?")
    public void refreshJwtKeyIfNeeded() {
        JwtKey currentKey = jwtKeyService.getActiveKey();
        if (currentKey.getExpiresAt().isBefore(LocalDateTime.now().plusDays(1))) {
            jwtKeyService.generateAndSaveNewKey();
        }
    }
}

