package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.entity.Present;
import org.example.entity.User;
import org.example.service.PresentService;
import org.example.service.UserService;
import org.example.view.PresentView;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Admin")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final PresentService presentService;
    private final UserService userService;

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
    @PatchMapping("/admin/stat/{presentId}")
    public Present updatePresentStatusById(@PathVariable Long presentId, @RequestBody String status) {
        presentService.updateStatusPresent(presentId, status);
        return presentService.getUserPresent(presentId);
    }

    @Operation(summary = "Удаление заявки")
    @DeleteMapping("/admin/del/{presentId}")
    public void deletePresentStatusById(@PathVariable Long presentId) {
        presentService.deletePresent(presentId);
    }

    @Operation(summary = "Изменение ответственного координатора")
    @PatchMapping("/admin/resp/{presentId}")
    public Present updatePresentResponsibleById(@PathVariable Long presentId, @RequestBody Long coordId, @RequestBody Long accId) {
        presentService.updateResponsiblePresent(presentId, coordId, accId);
        return presentService.getUserPresent(presentId);
    }

    @Operation(summary = "Просмотр всех пользователей по роли")
    @PostMapping("/admin/resp")
    public List<User> getAllResponsiblesByRole(@RequestBody int roleId) {
        return userService.findByRole(roleId);
    }


}
