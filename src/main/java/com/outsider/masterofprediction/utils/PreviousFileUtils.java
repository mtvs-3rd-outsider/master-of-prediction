package com.outsider.masterofprediction.utils;

import com.outsider.masterofprediction.dto.TblAttachmentDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class PreviousFileUtils {
    @Value("${file.upload-dir}")
    private String uploadDir;


    /**
     * tblAttachmentDTO 주소를 기반으로 uploadDir 경로를 붙여 파일을 생성합니다
     * @param tblAttachmentDTO 파일의 주소가 담긴 DTO
     * @return 생성한 파일 | IOException
     */
    public File create(TblAttachmentDTO tblAttachmentDTO) throws IOException {
        String previousFilePath = tblAttachmentDTO.getAttachmentFileAddress();
        Path file = Paths.get(uploadDir).resolve(previousFilePath);

        return new File(file.toString());
    }

    /**
     * 전달받은 파일을 제거합니다
     * @param previousFile 제거할 파일 객체
     * 결과를 출력합니다
     */
    public void delete(File previousFile) {
        if (previousFile.exists()) {
            if (previousFile.delete()) {
                System.out.println("Previous file deleted successfully.");
            } else {
                System.out.println("Failed to delete the previous file.");
            }
        }
    }


}
