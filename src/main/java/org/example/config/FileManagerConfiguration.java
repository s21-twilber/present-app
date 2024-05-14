package org.example.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@Data
@PropertySource("classpath:application.yml")
public class FileManagerConfiguration {

    @Value("${file.directory}")
    private String DIRECTORY_PATH;

    public void upload(byte[] file, String keyName) throws IOException {
         Path path = Paths.get(DIRECTORY_PATH, keyName);
         Path filePath = Files.createFile(path);
        FileOutputStream stream = new FileOutputStream(filePath.toString());
            stream.write(file);
            stream.close();
    }

    public Resource download(String key) throws IOException {
        Path path = Paths.get(DIRECTORY_PATH + key);
        Resource resource = new UrlResource(path.toUri());
//        System.out.println(path);
        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new IOException();
        }
    }
}
