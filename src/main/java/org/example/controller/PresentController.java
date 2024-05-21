package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.dto.PresentDto;
import org.example.entity.Present;
import org.example.service.FileService;
import org.example.service.PresentService;
import org.example.service.UserService;
import org.example.view.PresentView;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.security.Principal;
import java.util.List;


@Tag(name = "Present controller")
@RestController
@RequiredArgsConstructor
public class PresentController {

    private final PresentService presentService;
    private final UserService userService;
    private final FileService fileService;


    @Operation(summary = "Получение реестра заявок текущего пользователя")
    @GetMapping("/repository")
    public List<PresentView> getPresents(Principal principal) {
        Long userId = userService.findByEmail(principal.getName()).getId();
        List<PresentView> list = presentService.getUserPresents(userId);
        return list;
    }


    @Operation(summary = "Создание новой заявки")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/repository/create")
    public PresentView createPresent(Principal principal, @ModelAttribute PresentDto application) throws IOException {
        Long userId = userService.findByEmail(principal.getName()).getId();
        Present present = presentService.createNewPresent(application, userId);
        return new PresentView(present);
    }

    @Operation(summary = "Изменение заявки")
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/repository/{presentId}")
    public PresentView updatePresent(@PathVariable Long presentId,
                                     PresentDto application) throws IOException {
        Present present = presentService.updatePresent(application, presentId);
        return new PresentView(present);
    }

//    @Operation(summary = "Просмотр заявки")
//    @GetMapping("/repository/{presentId}")
//    public PresentView getPresentById(@PathVariable Long presentId) {
//        Present application = presentService.getUserPresent(presentId);
//        return new PresentView(application);
//    }

    @Operation(summary = "Удаление заявки")
    @DeleteMapping("/repository/{presentId}")
    public void deletePresentById(@PathVariable Long presentId) {
        presentService.deletePresent(presentId);
    }


    @Operation(summary = "Загрузка файлов заявки")
    @GetMapping(path = "/repository/{presentId}", produces = "application/zip")
    public void download(@PathVariable Long presentId, HttpServletResponse response) throws IOException {
        response.setContentType("Content-type: application/zip");
        response.setHeader("Content-Disposition",
                "attachment; filename=download.zip");
        fileService.download(response, presentId);
    }

    @Operation(summary = "Добавление фото")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, path = "/repository/{presentId}")
    public ResponseEntity<?> addFinalPhoto(Long presentId, @ModelAttribute MultipartFile file) throws IOException {
        presentService.updateFinalPhotoPresent(presentId, file);
        return ResponseEntity.ok().build();
    }
}
