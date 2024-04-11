package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.NewApplication;
import org.example.service.impl.ApplicationServiceImpl;
import org.example.service.impl.RegistryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final RegistryServiceImpl registryService;
    private final ApplicationServiceImpl applicationService;


    @GetMapping("/registry")
    public String userRegistry(Principal principal) {
        String username = principal.getName();
        return registryService.getRepository(username).toString();
    }

    @PostMapping("/registry/create")
    public ResponseEntity<?> createApplication(@RequestBody NewApplication application) {
        return applicationService.createNewApplication(application);
    }
}
