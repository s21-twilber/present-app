package org.example.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

public interface FileService {

    Set<String> upload(MultipartFile[] file) throws IOException;

    String upload(MultipartFile file) throws IOException;

    Set<String> findAllFilesByPresentId(Long id);

    Resource download(String key) throws IOException;

}
