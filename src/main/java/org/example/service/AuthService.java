package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.User;
import org.example.exception.*;
import org.example.jwt.*;
import org.example.repository.RoleRepository;
import org.example.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager manager;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest request) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUser registrationUser) {
        if (!registrationUser.getPassword().equals(registrationUser.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByEmail(registrationUser.getEmail()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Пользователь с указанным адресом электронной почты уже существует"),
                    HttpStatus.BAD_REQUEST);
        }
        User user = userService.createNewUser(registrationUser);
        return ResponseEntity.ok(new UserDto(user.getId(), user.getEmail()));
    }
}
