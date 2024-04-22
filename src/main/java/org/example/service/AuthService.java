package org.example.service;

import org.example.dto.RegistrationUserDto;
import org.example.exception.AppError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {

    ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUser);
}
