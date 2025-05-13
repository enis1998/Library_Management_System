package com.enis.library_management_system.controller.User.contract;

import com.enis.library_management_system.dto.User.UserRequestDto;
import com.enis.library_management_system.dto.User.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserContract {
    UserResponseDto getUserById(UUID id);
    UserResponseDto getUserByEmail(String email);
    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto);
}