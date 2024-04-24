package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.dto.PresentDto;
import org.example.entity.Present;
import org.example.service.UserService;
import org.example.service.impl.PresentServiceImpl;
import org.example.view.PresentView;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;


@Tag(name = "Application controller")
@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final PresentServiceImpl applicationService;
    private final UserService userService;


    @Operation(summary = "Получение реестра заявок текущего пользователя")
    @GetMapping("/registry")
    public List<Present> getApplications(Principal principal) {
        Long userId = userService.findByEmail(principal.getName()).getId();
        return applicationService.getRepository(userId);
    }


    @Operation(summary = "Создание новой заявки")
    @PostMapping("/registry/create")
    public PresentView createApplication(Principal principal, @RequestBody PresentDto application)
             {
        Long userId = userService.findByEmail(principal.getName()).getId();
        Present present = applicationService.createNewPresent(application, userId);
        return new PresentView(present);
    }

    @Operation(summary = "Просмотр заявки")
    @GetMapping("/registry/{appId}")
    public PresentView getApplicationById(Principal principal, @PathVariable Long appId) {
        Long userId = userService.findByEmail(principal.getName()).getId();
        Present application = applicationService.getUserPresent(userId, appId);
        return new PresentView(application);
    }

}
