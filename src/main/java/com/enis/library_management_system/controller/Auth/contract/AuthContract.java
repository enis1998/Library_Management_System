package com.enis.library_management_system.controller.Auth.contract;

import com.enis.library_management_system.dto.User.UserRequestDto;
import com.enis.library_management_system.dto.User.UserResponseDto;

public interface AuthContract {
    UserResponseDto registerUser(UserRequestDto userRequestDto);
}
