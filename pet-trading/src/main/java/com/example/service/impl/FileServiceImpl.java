package com.example.service.impl;

import com.example.config.MyConfig;
import com.example.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final MyConfig myConfig;

    @Override
    public String saveFile(MultipartFile file, String path) throws IOException {
        String basePath = Paths.get(myConfig.getUploadDirectory()).toAbsolutePath().toString();

        String originalFilename = file.getOriginalFilename();
        int lastIndexOfDot = originalFilename == null ? -1 : originalFilename.lastIndexOf(".");
        String suffix = lastIndexOfDot == -1 ? "" : originalFilename.substring(lastIndexOfDot);
        String uuidWithoutDash = UUID.randomUUID().toString();
        String filename = uuidWithoutDash + suffix;

        Path targetPath = Paths.get(basePath, path, filename);
        if (!Files.exists(targetPath.getParent())) {
            Files.createDirectories(targetPath.getParent());
        }
        file.transferTo(targetPath);

        return Paths.get(path, filename).toString().replace("\\", "/");
    }

    @Override
    public boolean deleteFile(String oldavatar) {
        String basePath = Paths.get(myConfig.getUploadDirectory()).toAbsolutePath().toString();
        return Paths.get(basePath, oldavatar).toFile().delete();
    }
}
