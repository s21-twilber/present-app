package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String fullName;
    private String birthDate;
    private String phoneNumber;
    private String position;
    private String department;
    private String address;
    private String employeeDate;
    private String role;
}
