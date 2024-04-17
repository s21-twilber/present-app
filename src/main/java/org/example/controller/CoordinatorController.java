package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.UserService;
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
    private final UserService userService;

    @GetMapping("/cregistry")
    public ResponseEntity<?> coordinatorRegistry(Principal principal) {
        Long userId = userService.findByEmail(principal.getName()).get().getId();
        return applicationService.getCoordinatorRepository(userId);
    }

    @GetMapping("/cregistry/{appId}")
    public ResponseEntity<?> applicationById(@PathVariable Long appId) {
        return applicationService.getUserApplication(appId);
    }
}
