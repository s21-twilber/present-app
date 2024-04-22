package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.RegistrationUserDto;
import org.example.dto.UserResponse;
import org.example.entity.User;
import org.example.exception.AppError;
import org.example.repository.RoleRepository;
import org.example.service.AuthService;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager manager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUser) {
        if (!registrationUser.getPassword().equals(registrationUser.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST, "Пароли не совпадают"),
                    HttpStatus.BAD_REQUEST);
        }
        if (userService.existsEmail(registrationUser.getEmail())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST,
                    "Пользователь с указанным адресом электронной почты уже существует"),
                    HttpStatus.BAD_REQUEST);
        }
        User user = userService.createNewUser(registrationUser);
        return ResponseEntity.ok(new UserResponse(user.getId(), user.getEmail()));
    }
}
