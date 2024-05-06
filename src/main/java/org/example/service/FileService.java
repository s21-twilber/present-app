package org.example.service;

import org.example.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    FileInfo upload(MultipartFile file) throws IOException;

}
