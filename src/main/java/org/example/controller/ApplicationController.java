package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.NewApplication;
import org.example.service.impl.ApplicationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationServiceImpl applicationService;


    @GetMapping("/registry/{userId}")
    public ResponseEntity<?> userRegistry(Principal principal, @PathVariable Long userId) {
        return applicationService.getRepository(userId);
    }

    @PostMapping("/registry/{userId}/create")
    public ResponseEntity<?> createApplication(@RequestBody NewApplication application, @PathVariable Long userId) {
        return applicationService.createNewApplication(application, userId);
    }

    @GetMapping("/registry/{userId}/{appId}")
    public ResponseEntity<?> applicationById(Principal principal, @PathVariable Long userId, @PathVariable Long appId) {
        return applicationService.getUserApplication(userId, appId);
    }

}
