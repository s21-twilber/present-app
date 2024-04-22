package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.PresentApplication;
import org.example.exception.AppError;
import org.example.service.UserService;
import org.example.service.impl.ApplicationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CoordinatorController {

    private final ApplicationServiceImpl applicationService;
    private final UserService userService;

    @GetMapping("/cregistry")
    public List<PresentApplication> getCoordinatorRegistry(Principal principal) {
        Long userId = userService.findByEmail(principal.getName()).getId();
        return applicationService.getCoordinatorApplications(userId);
    }

    @GetMapping("/cregistry/{appId}")
    public PresentApplication getApplicationById(@PathVariable Long appId) {
        return applicationService.getUserApplication(appId);
    }

    @PatchMapping("/cregistry/{appId}")
    public PresentApplication updateStatusApplicationById(@PathVariable Long appId, @RequestBody String status)
              {
        applicationService.updateStatusApplication(appId, status);
        return applicationService.getUserApplication(appId);
    }



}
