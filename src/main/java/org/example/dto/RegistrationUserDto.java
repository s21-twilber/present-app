package org.example.dto;

import lombok.Data;


@Data
public class RegistrationUserDto {
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;
    private String phoneNumber;
    private String department;
    private String position;
    private String address;
    private String birthDate;
    private String employeeDate;
}

