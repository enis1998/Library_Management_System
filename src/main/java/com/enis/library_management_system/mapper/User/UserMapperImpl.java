package com.enis.library_management_system.mapper.User;

import com.enis.library_management_system.dto.User.UserRequestDto;
import com.enis.library_management_system.dto.User.UserResponseDto;
import com.enis.library_management_system.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    public User toEntity(UserRequestDto dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setContactDetails(dto.getContactDetails());
        user.setRole(dto.getRole());
        return user;
    }

    public UserResponseDto toDto(User user) {
        if (user == null) {
            return null;
        }
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setContactDetails(user.getContactDetails());
        dto.setRole(user.getRole());
        return dto;
    }

    public User updateEntity(User user, UserRequestDto dto) {
        if (dto == null || user == null) {
            return null;
        }
        if (dto.getName() != null) {
            user.setName(dto.getName());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null) {
            user.setPassword(dto.getPassword());
        }
        if (dto.getContactDetails() != null) {
            user.setContactDetails(dto.getContactDetails());
        }
        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        }
        return user;
    }

}
