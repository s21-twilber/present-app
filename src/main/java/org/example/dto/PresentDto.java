package org.example.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@RequiredArgsConstructor
public class PresentDto {

    private int numChildren;
    private String commentChildren;
    private MultipartFile file;
    private String finalPhoto;

}
