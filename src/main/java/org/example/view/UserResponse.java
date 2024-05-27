package org.example.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserResponse {
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
