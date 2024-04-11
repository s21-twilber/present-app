package org.example.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NewApplication {

    private String email;
    private String fullName;
    private String birthDate;
    private String phoneNumber;
    private int numChildren;

}
