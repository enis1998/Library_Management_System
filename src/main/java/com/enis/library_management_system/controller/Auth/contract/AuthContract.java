package com.enis.library_management_system.controller.Auth.contract;

import com.enis.library_management_system.dto.User.UserRequestDto;
import com.enis.library_management_system.dto.User.UserResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthContract {
    ResponseEntity<UserResponseDto> registerUser(UserRequestDto userRequestDto);
}
