package org.example.service;

import org.example.dto.RegistrationUserDto;
import org.example.entity.User;
import org.example.enums.RolesEnum;
import org.example.exception.AppError;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;


public interface UserService extends UserDetailsService {
    User createNewUser(RegistrationUserDto registrationUser);
    List<User> getAll();
    User findByEmail(String email);
    User findById(Long id);
    List<User> findByRoleUser(RolesEnum role);
    List<User> findByRoleCoordinator(RolesEnum role);
    Boolean existsEmail(String email);
}
