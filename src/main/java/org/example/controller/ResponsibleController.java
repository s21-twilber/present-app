package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.entity.Present;
import org.example.service.UserService;
import org.example.service.impl.PresentServiceImpl;
import org.example.view.PresentView;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Tag(name = "Coordinator Controller")
@RestController
@RequiredArgsConstructor
public class ResponsibleController {

    private final PresentServiceImpl presentService;
    private final UserService userService;


    @Operation(summary = "Просмотр заявок ответствееного координатора или бухгалтера")
    @GetMapping("/crepository")
    public List<PresentView> getResponsiblePresents(Principal principal) {
        Long userId = userService.findByEmail(principal.getName()).getId();
        return presentService.getResponsiblePresents(userId);
    }

    @Operation(summary = "Просмотр заявки координатора или бухгалтера")
    @GetMapping("/crepository/{appId}")
    public Present getPresentById(@PathVariable Long appId) {
        return presentService.getUserPresent(appId);
    }

    @Operation(summary = "Изменение статуса заявки")
    @PatchMapping("/crepository/{appId}")
    public Present updatePresentStatusById(@PathVariable Long appId, @RequestBody String status) {
        presentService.updateStatusPresent(appId, status);
        return presentService.getUserPresent(appId);
    }

    @Operation(summary = "Удаление заявки")
    @DeleteMapping("/crepository/{appId}")
    public void updatePresentStatusById(@PathVariable Long appId) {
        presentService.deletePresent(appId);
    }



}
