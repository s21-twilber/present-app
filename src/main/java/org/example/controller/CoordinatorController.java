package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.entity.PresentApplication;
import org.example.exception.AppError;
import org.example.service.UserService;
import org.example.service.impl.ApplicationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Tag(name = "Coordinator Controller")
@RestController
@RequiredArgsConstructor
public class CoordinatorController {

    private final ApplicationServiceImpl applicationService;
    private final UserService userService;


    @Operation(summary = "Просмотр заявок ответствееного координатора")
    @GetMapping("/cregistry")
    public List<PresentApplication> getCoordinatorRegistry(Principal principal) {
        Long userId = userService.findByEmail(principal.getName()).getId();
        return applicationService.getCoordinatorApplications(userId);
    }

    @Operation(summary = "Просмотр заявки координатора")
    @GetMapping("/cregistry/{appId}")
    public PresentApplication getApplicationById(@PathVariable Long appId) {
        return applicationService.getUserApplication(appId);
    }

    @Operation(summary = "Изменение статуса заявки")
    @PatchMapping("/cregistry/{appId}")
    public PresentApplication updateStatusApplicationById(@PathVariable Long appId, @RequestBody String status) {
        applicationService.updateStatusApplication(appId, status);
        return applicationService.getUserApplication(appId);
    }

    @Operation(summary = "Удаление заявки")
    @DeleteMapping("/cregistry/{appId}")
    public void updateStatusApplicationById(@PathVariable Long appId) {
        applicationService.deleteApplication(appId);
    }



}
