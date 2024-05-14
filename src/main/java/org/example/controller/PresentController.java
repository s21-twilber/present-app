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
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


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
        ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
        Set<String> foundFile = fileService.findAllFilesByPresentId(presentId);
        for (String f : foundFile) {
            Resource resource = fileService.download(f);
            ZipEntry zipEntry = new ZipEntry(f);
            zipEntry.setSize(foundFile.size());
            zipOut.putNextEntry(zipEntry);
            StreamUtils.copy(resource.getInputStream(), zipOut);
            zipOut.closeEntry();
        }
        zipOut.finish();
        zipOut.close();
    }

    @Operation(summary = "Добавление фото")
    @PostMapping(path = "/repository/{presentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addFinalPhoto(Long presentId, MultipartFile file) throws IOException {
        presentService.updateFinalPhotoPresent(presentId, file);
        return ResponseEntity.ok().build();
    }
}
