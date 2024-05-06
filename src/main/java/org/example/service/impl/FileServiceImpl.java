package org.example.service.impl;

import org.example.config.FileManagerConfiguration;
import org.example.entity.FileInfo;
import org.example.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class FileServiceImpl implements FileService {

    private final FileManagerConfiguration fileManager;

    public FileServiceImpl(FileManagerConfiguration fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public FileInfo upload(MultipartFile file) throws IOException {
        fileManager.upload(file.getBytes(), generateKey(file.getOriginalFilename()));
        return FileInfo.builder()
                .name(file.getOriginalFilename())
                .size(file.getSize())
                .build();
    }

    private String generateKey(String name) {
        String fullName = name + LocalDateTime.now().toString();
        return DigestUtils.md5DigestAsHex(fullName.getBytes());
    }
}
