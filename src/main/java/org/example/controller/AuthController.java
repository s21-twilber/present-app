package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.dto.RegistrationUserDto;
import org.example.dto.UserRequest;
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

    @Operation(summary = "Регистрация нового пользователя")
    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUser) {
        return authService.createNewUser(registrationUser);
    }

    @Operation(summary = "Вход в систему")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/login")
    public void login(@RequestBody UserRequest request) {
    }

    @Operation(summary = "Выход из системы")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
    }

    @GetMapping("/")
    public void hello() {
    }

}
