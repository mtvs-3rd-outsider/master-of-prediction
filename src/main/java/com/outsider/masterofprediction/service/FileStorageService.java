package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileStorageService {
    @Value("${file.upload-dir}")
    private String uploadDir;

    public File storeFile(MultipartFile file) {
        // Ensure the directory exists

        checkUploadDir();
        // Save the file
        File destination = createUniqueFilePath(file);
        try {
            file.transferTo(destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return destination;
    }

    // uploadDir 경로가 존재하는지 확인하고 없으면 생성 실패시 RUNTIME EXCEPTION
    public void checkUploadDir() {
        File uploadDir = new File(this.uploadDir);
        if (!uploadDir.exists()) {
            if (!uploadDir.mkdirs()) {
                throw new RuntimeException("Failed to create upload directory!");
            }
        }
    }

    // 입력받은 파일의 이름으로 유니크한 파일 이름을 생성하여 경로를 추가한 파일 객체를 반환합니다
    public File createUniqueFilePath(MultipartFile file) {
        String uniqueFileName = FileUtil.generateUniqueFileName(file.getOriginalFilename());
        Path filepath = Paths.get(uploadDir).resolve(uniqueFileName);
        return new File(filepath.toString());
    }

}
