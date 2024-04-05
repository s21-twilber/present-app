package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.jwt.JwtRequest;
import org.example.jwt.RegistrationUser;
import org.example.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest request) {
        return authService.createAuthToken(request);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUser registrationUser) {
        return authService.createNewUser(registrationUser);
    }

}
