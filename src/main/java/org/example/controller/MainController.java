package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.RegistryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {

    private RegistryService registryService;

    @GetMapping("/unsecured")
    public String unsecuredLate() {
        return "unsecuredLate";
    }

    @GetMapping("/secured")
    public String securedLate() {
        return "securedLate";
    }

    @GetMapping("/coordinator")
    public String coordinatorLate() {
        return "coordinatorLate";
    }

    @GetMapping("/registry")
    public String userRegistry(Principal principal) {
        String username = principal.getName();
        return registryService.getRepository(username).toString();
    }

    @GetMapping("/admin")
    public String adminLate() {
        return "adminLate";
    }

    @GetMapping("/info")
    public String infoUser(Principal principal) {
        return principal.getName();
    }
}
