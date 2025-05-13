package com.enis.library_management_system.controller.Auth;

import com.enis.library_management_system.controller.Auth.contract.AuthContract;
import com.enis.library_management_system.dto.User.UserRequestDto;
import com.enis.library_management_system.dto.User.UserResponseDto;
import com.enis.library_management_system.enums.Role;
import com.enis.library_management_system.security.CustomUserDetailsService;
import com.enis.library_management_system.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthContract authContract;
    private final JwtUtil jwtUtil;

    public AuthController(AuthContract authContract, JwtUtil jwtUtil) {
        this.authContract = authContract;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register/patron")
    public ResponseEntity<?> registerPatron(@Valid @RequestBody UserRequestDto registerRequest) {
        try {
            registerRequest.setRole(Role.PATRON);

            UserResponseDto registeredUser = authContract.registerUser(registerRequest);

            long expirationInMs = TimeUnit.DAYS.toMillis(7);
            String jwt = jwtUtil.generateToken(registerRequest.getEmail(), expirationInMs);

            Map<String, Object> response = new HashMap<>();
            response.put("user", registeredUser);
            response.put("token", jwt);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/register/librarian")
    public ResponseEntity<?> registerLibrarian(@Valid @RequestBody UserRequestDto registerRequest) {
        try {
            registerRequest.setRole(Role.LIBRARIAN);

            UserResponseDto registeredUser = authContract.registerUser(registerRequest);

            long expirationInMs = TimeUnit.DAYS.toMillis(7);
            String jwt = jwtUtil.generateToken(registerRequest.getEmail(), expirationInMs);

            Map<String, Object> response = new HashMap<>();
            response.put("user", registeredUser);
            response.put("token", jwt);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}