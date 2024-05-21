package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.dto.StatusDto;
import org.example.entity.Present;
import org.example.service.UserService;
import org.example.service.impl.PresentServiceImpl;
import org.example.view.PresentView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Tag(name = "Responsible Controller")
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
    @GetMapping("/crepository/{presentId}")
    public Present getPresentById(@PathVariable Long presentId) {
        return presentService.getUserPresent(presentId);
    }

    @Operation(summary = "Изменение статуса заявки")
    @PatchMapping("/crepository/{presentId}")
    public ResponseEntity<?> updatePresentStatusById(@PathVariable Long presentId,
                                                     @RequestBody(required = false) StatusDto statusDto) {
        presentService.updateStatusPresent(presentId, statusDto.getStatus());
        presentService.addStatusComment(presentId, statusDto.getCommentStatus());
        return ResponseEntity.ok(presentService.getUserPresent(presentId));
    }

    @Operation(summary = "Удаление заявки")
    @DeleteMapping("/crepository/{presentId}")
    public void deletePresentById(@PathVariable Long presentId) {
        presentService.deletePresent(presentId);
    }



}
