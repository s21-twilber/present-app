package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {


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

    @GetMapping("/admin")
    public String adminLate() {
        return "adminLate";
    }

    @GetMapping("/info")
    public String infoUser(Principal principal) {
        return principal.getName();
    }
}
