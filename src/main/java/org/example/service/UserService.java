package org.example.service;

import org.example.dto.RegistrationUserDto;
import org.example.entity.User;
import org.example.enums.RolesEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {
    User createNewUser(RegistrationUserDto registrationUser);
    User updateUser(Long userId, RegistrationUserDto registrationUser);
    ResponseEntity<?> updateRole(Long userId, String roleName);
    List<User> getAll();
    User findByEmail(String email);
    User findById(Long id);
    List<User> findByRole(RolesEnum role);
    List<User> findByRole(int roleId);
    Boolean existsEmail(String email);
}
