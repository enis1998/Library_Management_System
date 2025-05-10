package com.enis.library_management_system.dto.User;

import com.enis.library_management_system.enums.Role;

import java.util.UUID;

public class UserResponseDto {
    private UUID id;

    private String name;

    private String email;

    private String contactDetails;

    private Role role;

}
