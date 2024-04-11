package org.example.dto;

import lombok.Data;


@Data
public class RegistrationUser {
    private String password;
    private String confirmPassword;
    private String email;
    private Integer role;

}

