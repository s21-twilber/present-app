package org.example.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NewApplication {

    private String phoneNumber;
    private int numChildren;

}
