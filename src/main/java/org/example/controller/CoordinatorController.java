package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.RegistrationUser;
import org.example.entity.PresentApplication;
import org.example.enums.StatusesEnum;
import org.example.service.UserService;
import org.example.service.impl.ApplicationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class CoordinatorController {

    private final ApplicationServiceImpl applicationService;
    private final UserService userService;

    @GetMapping("/cregistry")
    public ResponseEntity<?> getCoordinatorRegistry(Principal principal) {
        Long userId = userService.findByEmail(principal.getName()).get().getId();
        return applicationService.getCoordinatorRepository(userId);
    }

    @GetMapping("/cregistry/{appId}")
    public ResponseEntity<?> getApplicationById(@PathVariable Long appId) {
        return applicationService.getUserApplication(appId);
    }

    @PostMapping("/cregistry/{appId}")
    public ResponseEntity<?> updateStatusApplicationById(@PathVariable Long appId, @RequestBody StatusesEnum status) {
        applicationService.updateStatusApplication(appId, status);
        return applicationService.getUserApplication(appId);
    }
}
