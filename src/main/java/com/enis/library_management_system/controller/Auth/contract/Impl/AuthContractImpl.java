package com.enis.library_management_system.controller.Auth.contract.Impl;

import com.enis.library_management_system.controller.Auth.contract.AuthContract;
import com.enis.library_management_system.dto.User.UserRequestDto;
import com.enis.library_management_system.dto.User.UserResponseDto;
import com.enis.library_management_system.entity.User;
import com.enis.library_management_system.mapper.User.UserMapper;
import com.enis.library_management_system.service.User.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthContractImpl implements AuthContract {
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthContractImpl(UserService userService, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        if (userService.existsByEmail(userRequestDto.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        User user = userMapper.toEntity(userRequestDto);
        User savedUser = userService.save(user);
        return userMapper.toDto(savedUser);

    }
}
