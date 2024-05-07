package org.example.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Builder
@Data
public class FileInfo {

    private Long id;

    private String name;

    private String key;

    private Long size;
}
