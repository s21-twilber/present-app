package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.dto.RegistrationUserDto;
import org.example.dto.UserRequest;
import org.example.view.UserResponse;
import org.example.entity.User;
import org.example.service.UserService;
import org.example.service.impl.AuthServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Tag(name = "Authentication")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;
    private final UserService userService;

    @Operation(summary = "Регистрация нового пользователя")
    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUser) {
        return authService.createNewUser(registrationUser);
    }

    @Operation(summary = "Вход в систему")
    @PostMapping(value = "/login", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void login(@RequestBody UserRequest request) {
    }

    @GetMapping(value = "/login")
    public void login() {
    }

    @Operation(summary = "Выход из системы")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
    }


    @Operation(summary = "Получение пользователя")
    @GetMapping("/")
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        User user = userService.findByEmail(request.getRemoteUser());
        if (user != null) {
            return ResponseEntity.ok(new UserResponse(user.getId(), user.getEmail(), user.getRole().getName().name()));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @Operation(summary = "Получение данных пользователя")
    @GetMapping("/user")
    public ResponseEntity<?> getUserInfo(HttpServletRequest request) {
        User user = userService.findByEmail(request.getRemoteUser());
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @Operation(summary = "Изменение данных пользователя")
    @PatchMapping("/user")
    public ResponseEntity<?> updateUserInfo(@RequestBody RegistrationUserDto registrationUser,
                                            HttpServletRequest request) {
        User user = userService.findByEmail(request.getRemoteUser());
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
