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


    @GetMapping("/registry")
    public ResponseEntity<?> userRegistry(Principal principal) {
        Long userId = userService.findByEmail(principal.getName()).get().getId();
        return applicationService.getRepository(userId);
    }


    @PostMapping("/registry/create")
    public ResponseEntity<?> createApplication(Principal principal, @RequestBody NewApplication application) {
        Long userId = userService.findByEmail(principal.getName()).get().getId();
        return applicationService.createNewApplication(application, userId);
    }

    @GetMapping("/registry/{appId}")
    public ResponseEntity<?> applicationById(Principal principal, @PathVariable Long appId) {
        Long userId = userService.findByEmail(principal.getName()).get().getId();
        return applicationService.getUserApplication(userId, appId);
    }

}
