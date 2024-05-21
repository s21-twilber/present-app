package org.example.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StatusDto {

    private String status;
    private String commentStatus;

}
