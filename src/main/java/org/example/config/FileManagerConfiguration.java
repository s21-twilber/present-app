package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileManagerConfiguration {

    public void upload(byte[] file, String keyName) throws IOException {
        // Путь
         Path path = Paths.get("C:\\Users\\alekseeva\\Desktop\\Подарки", keyName);
         Path filePath = Files.createFile(path);
        FileOutputStream stream = new FileOutputStream(filePath.toString());
            stream.write(file);
            stream.close();
    }
}
