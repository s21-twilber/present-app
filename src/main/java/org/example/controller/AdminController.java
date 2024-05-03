package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.entity.Present;
import org.example.service.PresentService;
import org.example.view.PresentView;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/admin/{presentId}")
    public Present getPresentById(@PathVariable Long presentId) {
        return presentService.getUserPresent(presentId);
    }

    @Operation(summary = "Изменение статуса заявки")
    @PatchMapping("/admin/{presentId}")
    public Present updatePresentStatusById(@PathVariable Long presentId, @RequestBody String status) {
        presentService.updateStatusPresent(presentId, status);
        return presentService.getUserPresent(presentId);
    }

    @Operation(summary = "Удаление заявки")
    @DeleteMapping("/admin/{presentId}")
    public void updatePresentStatusById(@PathVariable Long presentId) {
        presentService.deletePresent(presentId);
    }


}
