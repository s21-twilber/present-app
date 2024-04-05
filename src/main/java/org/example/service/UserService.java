package org.example.service;

import org.example.entity.User;
import org.example.exception.EmailExistsException;
import org.example.jwt.RegistrationUser;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserService extends UserDetailsService {
    User createNewUser(RegistrationUser registrationUser);
    List<User> getAll();
    void update(String name, Long id) throws EmailExistsException;
    void setPasswordEncoder(@Lazy PasswordEncoder passwordEncoder);
    boolean deleteById(UUID id);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
