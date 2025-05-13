package com.enis.library_management_system.service.Jwt;

import com.enis.library_management_system.entity.JwtKey;

public interface JwtKeyService {

    JwtKey generateAndSaveNewKey();

    String generateSecretKey();

    JwtKey getActiveKey();
}

