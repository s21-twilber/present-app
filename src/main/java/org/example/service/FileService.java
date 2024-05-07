package org.example.service;

import org.example.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    List<String> upload(MultipartFile[] file) throws IOException;

}
