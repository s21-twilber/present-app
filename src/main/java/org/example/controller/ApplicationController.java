package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.PresentDto;
import org.example.exception.AppError;
import org.example.service.UserService;
import org.example.service.impl.ApplicationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;


@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationServiceImpl applicationService;
    private final UserService userService;


    @GetMapping("/registry")
    public ResponseEntity<?> getUserRegistry(Principal principal) {
        Long userId = userService.findByEmail(principal.getName()).get().getId();
        return applicationService.getRepository(userId);
    }


    @PostMapping("/registry/create")
    public ResponseEntity<?> createApplication(Principal principal, @RequestBody PresentDto application)
            throws AppError {
        Long userId = userService.findByEmail(principal.getName()).get().getId();
        return applicationService.createNewApplication(application, userId);
    }

    @GetMapping("/registry/{appId}")
    public ResponseEntity<?> getApplicationById(Principal principal, @PathVariable Long appId) {
        Long userId = userService.findByEmail(principal.getName()).get().getId();
        return applicationService.getUserApplication(userId, appId);
    }

}
