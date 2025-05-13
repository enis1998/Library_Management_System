package com.enis.library_management_system.mapper.User;

import com.enis.library_management_system.dto.User.UserRequestDto;
import com.enis.library_management_system.dto.User.UserResponseDto;
import com.enis.library_management_system.entity.User;

public interface UserMapper {

    User toEntity(UserRequestDto dto);

    UserResponseDto toDto(User user);

    User updateEntity(User user, UserRequestDto dto);
}