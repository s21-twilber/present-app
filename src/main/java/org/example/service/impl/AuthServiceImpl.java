package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.RegistrationUserDto;
import org.example.dto.UserResponse;
import org.example.dto.Request;
import org.example.entity.User;
import org.example.exception.AppError;
import org.example.repository.RoleRepository;
import org.example.service.AuthService;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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


    public ResponseEntity<?> login(@RequestBody Request request) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(),
                    "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        return ResponseEntity.ok(userDetails);
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUser) throws AppError {
        if (!registrationUser.getPassword().equals(registrationUser.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByEmail(registrationUser.getEmail()).isPresent()) {
//            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
//                    "Пользователь с указанным адресом электронной почты уже существует"),
//                    HttpStatus.BAD_REQUEST);
            throw new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Пользователь с указанным адресом электронной почты уже существует");
        }
        User user = userService.createNewUser(registrationUser);
        return ResponseEntity.ok(new UserResponse(user.getId(), user.getEmail()));
    }
}
