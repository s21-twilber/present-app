package org.example.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

public interface FileService {

    Set<String> upload(MultipartFile[] file) throws IOException;

    String upload(MultipartFile file) throws IOException;

    Optional<Set<String>> findAllFilesByPresentId(Long id);

    void download(HttpServletResponse response, Long presentId) throws IOException;

    Resource downloadFile(String key) throws IOException;

}
