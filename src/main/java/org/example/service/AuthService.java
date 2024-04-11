package org.example.service;

import org.example.dto.RegistrationUser;
import org.example.dto.jwt.JwtRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {

    ResponseEntity<?> createAuthToken(@RequestBody JwtRequest request);

    ResponseEntity<?> createNewUser(@RequestBody RegistrationUser registrationUser);
}
