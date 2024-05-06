package org.example.dto;

import lombok.Data;


@Data
public class RegistrationUserDto {
    private String password;
    private String confirmPassword;
    private String email;
    private String phoneNumber;
    private String fullName;
    private String department;
    private String position;
    private String birthDate;
    private String employeeDate;
}

