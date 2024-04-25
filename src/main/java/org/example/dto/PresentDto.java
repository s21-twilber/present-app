package org.example.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PresentDto {

    private int numChildren;
    private String commentChildren;
    private String filesRef;
    private String finalPhoto;

}
