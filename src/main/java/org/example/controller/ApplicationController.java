package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.PresentDto;
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
public class ApplicationController {

    private final ApplicationServiceImpl applicationService;
    private final UserService userService;


    @GetMapping("/registry")
    public List<PresentApplication> getApplications(Principal principal) {
        Long userId = userService.findByEmail(principal.getName()).getId();
        return applicationService.getRepository(userId);
    }


    @PostMapping("/registry/create")
    public PresentApplication createApplication(Principal principal, @RequestBody PresentDto application)
             {
        Long userId = userService.findByEmail(principal.getName()).getId();
        return applicationService.createNewApplication(application, userId);
    }

    @GetMapping("/registry/{appId}")
    public PresentApplication getApplicationById(Principal principal, @PathVariable Long appId) {
        Long userId = userService.findByEmail(principal.getName()).getId();
        return applicationService.getUserApplication(userId, appId);
    }

}
