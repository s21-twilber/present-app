package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.dto.PresentDto;
import org.example.entity.Present;
import org.example.service.FileService;
import org.example.service.UserService;
import org.example.service.impl.PresentServiceImpl;
import org.example.view.PresentView;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Tag(name = "Present controller")
@RestController
@RequiredArgsConstructor
public class PresentController {

    private final PresentServiceImpl presentService;
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

    @Operation(summary = "Просмотр заявки")
    @GetMapping("/repository/{presentId}")
    public PresentView getPresentById(Principal principal, @PathVariable Long presentId) {
        Present application = presentService.getUserPresent(presentId);
        return new PresentView(application);
    }

    @Operation(summary = "Удаление заявки")
    @DeleteMapping("/repository/{presentId}")
    public void deletePresentById(@PathVariable Long presentId) {
        presentService.deletePresent(presentId);
    }

    @GetMapping(path = "/repository/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> download(@PathVariable("id") Long id) throws IOException {
        List<String> foundFile = fileService.findAllFilesByPresentId(id);
        List<Resource> res = new ArrayList<>();
        for (String f : foundFile) {
                Resource resource = fileService.download(f);
                res.add(resource);
        }
        return ResponseEntity.ok().body(res.get(0));
    }
}
