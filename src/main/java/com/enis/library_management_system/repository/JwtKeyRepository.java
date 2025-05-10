package com.enis.library_management_system.repository;

import com.enis.library_management_system.entity.JwtKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JwtKeyRepository extends JpaRepository<JwtKey, UUID> {

    Optional<JwtKey> findFirstByExpiresAtAfterOrderByCreatedAtDesc(LocalDateTime now);

}

