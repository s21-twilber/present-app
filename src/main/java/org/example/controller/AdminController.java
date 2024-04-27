package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.entity.Present;
import org.example.service.PresentService;
import org.example.view.PresentView;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Tag(name = "Admin")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final PresentService presentService;

    @Operation(summary = "Просмотр всех заявок")
    @GetMapping("/admin")
    public List<PresentView> getAllPresents() {
        return presentService.getAllPresents();
    }

    @Operation(summary = "Просмотр конкретной заявки")
    @GetMapping("/admin/{appId}")
    public Present getPresentById(@PathVariable Long appId) {
        return presentService.getUserPresent(appId);
    }

    @Operation(summary = "Изменение статуса заявки")
    @PatchMapping("/admin/{appId}")
    public Present updatePresentStatusById(@PathVariable Long appId, @RequestBody String status) {
        presentService.updateStatusPresent(appId, status);
        return presentService.getUserPresent(appId);
    }

    @Operation(summary = "Удаление заявки")
    @DeleteMapping("/admin/{appId}")
    public void updatePresentStatusById(@PathVariable Long appId) {
        presentService.deletePresent(appId);
    }


}
