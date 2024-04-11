package org.example.service;

import org.example.dto.RegistrationUser;
import org.example.entity.User;
import org.example.enums.RolesEnum;
import org.example.exception.EmailExistsException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;


public interface UserService extends UserDetailsService {
    User createNewUser(RegistrationUser registrationUser);
    List<User> getAll();
    void setPasswordEncoder(@Lazy PasswordEncoder passwordEncoder);
    Optional<User> findByEmail(String email);
    List<User> findByRoleUser(RolesEnum role);
    List<User> findByRoleCoordinator(RolesEnum role);
}
