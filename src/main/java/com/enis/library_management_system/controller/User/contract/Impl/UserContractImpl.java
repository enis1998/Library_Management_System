package com.enis.library_management_system.controller.User.contract.Impl;

import com.enis.library_management_system.controller.User.contract.UserContract;
import com.enis.library_management_system.dto.User.UserRequestDto;
import com.enis.library_management_system.dto.User.UserResponseDto;
import com.enis.library_management_system.entity.User;
import com.enis.library_management_system.mapper.User.UserMapper;
import com.enis.library_management_system.service.User.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserContractImpl implements UserContract {

    private final UserService userService;
    private final UserMapper mapper;
    private final UserMapper userMapper;

    public UserContractImpl(UserService userService, UserMapper mapper, UserMapper userMapper) {
        this.userService = userService;
        this.mapper = mapper;
        this.userMapper = userMapper;
    }


    @Override
    public UserResponseDto getUserById(UUID id) {
        User user = userService.getUserById(id);
        return mapper.toDto(user);
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        User user = userService.getUserByEmail(email);
        return mapper.toDto(user);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = mapper.toEntity(userRequestDto);
        user = userService.save(user);
        return mapper.toDto(user);
    }

    @Override
    public UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto) {
        User user = userService.getUserById(id);
        user = userMapper.updateEntity(user, userRequestDto);
        user = userService.save(user);
        return mapper.toDto(user);
    }
}