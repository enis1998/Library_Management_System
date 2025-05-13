package com.enis.library_management_system.service.User;

import com.enis.library_management_system.dto.User.UserRequestDto;
import com.enis.library_management_system.dto.User.UserResponseDto;
import com.enis.library_management_system.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User save(User user);
    User getUserById(UUID id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    User updateUser(User user, UserRequestDto userRequestDto);
    void deleteUser(UUID id);
    boolean existsByEmail(String email);

}
