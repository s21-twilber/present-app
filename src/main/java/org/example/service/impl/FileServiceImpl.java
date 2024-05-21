package org.example.service.impl;

import org.example.config.FileManagerConfiguration;
import org.example.entity.FileInfo;
import org.example.entity.Present;
import org.example.exception.NotFoundException;
import org.example.service.FileService;
import org.example.service.PresentService;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class FileServiceImpl implements FileService {

    private final FileManagerConfiguration fileManager;
    private final PresentService presentService;

    public FileServiceImpl(FileManagerConfiguration fileManager, @Lazy PresentService presentService) {
        this.fileManager = fileManager;
        this.presentService = presentService;
    }

    @Override
    public Set<String> upload(MultipartFile[] file) throws IOException {
        Set<String> files = new HashSet<>();
        for (MultipartFile f : file) {
            String key = generateKey(f.getOriginalFilename());
            fileManager.upload(f.getBytes(), key);
            FileInfo createdFile = FileInfo.builder()
                    .name(f.getOriginalFilename())
                    .key(key)
                    .size(f.getSize())
                    .build();

            files.add(createdFile.getKey());
        }
        return files;
    }

    @Override
    public String upload(MultipartFile file) throws IOException {
        String key = generateKey(file.getOriginalFilename());
        fileManager.upload(file.getBytes(), key);
        FileInfo createdFile = FileInfo.builder()
                .name(file.getOriginalFilename())
                .key(key)
                .size(file.getSize())
                .build();

        return createdFile.getKey();
    }

    @Override
    public Optional<Set<String>> findAllFilesByPresentId(Long id) {
        Present present = presentService.getUserPresent(id);
        return Optional.of(present.getFilesRef());
    }

    @Override
    public void download(HttpServletResponse response, Long presentId) throws IOException {
        ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
        Set<String> foundFile = findAllFilesByPresentId(presentId).orElseThrow(() ->
                new NotFoundException("Files not found"));
            for (String f : foundFile) {
                Resource resource = downloadFile(f);
                ZipEntry zipEntry = new ZipEntry(f);
                zipEntry.setSize(foundFile.size());
                zipOut.putNextEntry(zipEntry);
                StreamUtils.copy(resource.getInputStream(), zipOut);
                zipOut.closeEntry();
            }
            zipOut.finish();
            zipOut.close();
    }

    @Override
    public Resource downloadFile(String key) throws IOException {
        return fileManager.download(key);
    }

    private String generateKey(String name) {
        String fullName = name + LocalDateTime.now().toString();
        return DigestUtils.md5DigestAsHex(fullName.getBytes());
    }
}
