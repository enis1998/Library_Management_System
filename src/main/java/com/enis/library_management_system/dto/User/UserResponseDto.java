package com.enis.library_management_system.dto.User;

import com.enis.library_management_system.enums.Role;

import java.util.UUID;

public class UserResponseDto {
    private UUID id;

    private String name;

    private String email;

    private String contactDetails;

    private Role role;

    public UserResponseDto() {
    }

    public UserResponseDto(String name, String email, String contactDetails, Role role) {
        this.name = name;
        this.email = email;
        this.contactDetails = contactDetails;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
