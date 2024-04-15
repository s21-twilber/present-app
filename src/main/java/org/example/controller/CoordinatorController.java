package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.impl.ApplicationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class CoordinatorController {

    private final ApplicationServiceImpl applicationService;

    @GetMapping("/cregistry/{userId}")
    public ResponseEntity<?> coordinatorRegistry(Principal principal, @PathVariable Long userId) {
        return applicationService.getCoordinatorRepository(userId);
    }

    @GetMapping("/cregistry/{userId}/{appId}")
    public ResponseEntity<?> applicationById(Principal principal, @PathVariable Long userId, @PathVariable Long appId) {
        return applicationService.getUserApplication(appId);
    }
}
