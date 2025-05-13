package com.enis.library_management_system.controller.Auth;

import com.enis.library_management_system.dto.User.UserRequestDto;
import com.enis.library_management_system.security.CustomUserDetailsService;
import com.enis.library_management_system.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserRequestDto loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        // 7 gün = 7 * 24 saat * 60 dakika * 60 saniye * 1000 milisaniye
        long expirationInMs = TimeUnit.DAYS.toMillis(7);

        String jwt = jwtUtil.generateToken(loginRequest.getEmail(), expirationInMs);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    public record AuthResponse(String token) { }
}