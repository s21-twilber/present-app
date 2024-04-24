package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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


@Tag(name = "Application controller")
@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationServiceImpl applicationService;
    private final UserService userService;


    @Operation(summary = "Получение реестра заявок текущего пользователя")
    @GetMapping("/registry")
    public List<PresentApplication> getApplications(Principal principal) {
        Long userId = userService.findByEmail(principal.getName()).getId();
        return applicationService.getRepository(userId);
    }


    @Operation(summary = "Создание новой заявки")
    @PostMapping("/registry/create")
    public PresentApplication createApplication(Principal principal, @RequestBody PresentDto application)
             {
        Long userId = userService.findByEmail(principal.getName()).getId();
        return applicationService.createNewApplication(application, userId);
    }

    @Operation(summary = "Просмотр заявки")
    @GetMapping("/registry/{appId}")
    public PresentApplication getApplicationById(Principal principal, @PathVariable Long appId) {
        Long userId = userService.findByEmail(principal.getName()).getId();
        return applicationService.getUserApplication(userId, appId);
    }

}
