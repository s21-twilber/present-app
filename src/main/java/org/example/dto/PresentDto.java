package org.example.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PresentDto {

    private String phoneNumber;
    private int numChildren;

}
