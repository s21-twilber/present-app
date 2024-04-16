package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.NewApplication;
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


    @GetMapping("/registry/{userId}")
    public ResponseEntity<?> userRegistry(Principal principal, @PathVariable Long userId) {
        System.out.println(principal.getName());
        return applicationService.getRepository(userId);
    }

    @GetMapping("/registry")
    public ResponseEntity<?> userRegistry2(Principal principal) {
        Long id = userService.findByEmail(principal.getName()).get().getId();
        return applicationService.getRepository(id);
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
