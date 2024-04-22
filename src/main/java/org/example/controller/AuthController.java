package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.RegistrationUserDto;
import org.example.exception.AppError;
import org.example.service.impl.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUser)
             {
        return authService.createNewUser(registrationUser);
    }

}
