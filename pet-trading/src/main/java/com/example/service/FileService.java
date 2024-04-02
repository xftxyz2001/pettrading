package com.example.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    // 保存文件
    String saveFile(MultipartFile file, String path) throws IOException;

    boolean deleteFile(String oldavatar);
}
