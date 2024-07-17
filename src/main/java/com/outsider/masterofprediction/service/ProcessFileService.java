package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.TblAttachmentDTO;
import com.outsider.masterofprediction.dto.constatnt.StringConstants;
import com.outsider.masterofprediction.utils.FileUtil;
import com.outsider.masterofprediction.utils.PreviousFileUtils;
import com.outsider.masterofprediction.utils.ProcessFileInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Service
public class ProcessFileService {

    private final FileStorageService fileStorageService;
    private final PreviousFileUtils previousFileUtils;


    @Autowired
    public ProcessFileService(FileStorageService fileStorageService, PreviousFileUtils previousFileUtils) {
        this.fileStorageService = fileStorageService;
        this.previousFileUtils = previousFileUtils;
    }

    /**
     *  파일을 업데이트하는 메소드
     * @param tblAttachmentDTO 데이터베이스에 저장된 파일 정보
     * @param profileImage  사용자에게 받은 파일
     * @param processFileInterface 데이터베이스 사진을 업데이트하는 메소드
     * @throws IOException
     */
    public void execute(TblAttachmentDTO tblAttachmentDTO, MultipartFile profileImage,
                        ProcessFileInterface processFileInterface) throws IOException {
        File uploadFile = null;
        try {
            // 이전 파일 객체 생성
            File previousFile = previousFileUtils.create(tblAttachmentDTO);

            // 유니크한 파일 이름 생성 및 저장
            uploadFile = fileStorageService.storeFile(profileImage);

            if (!FileUtil.compareFiles(uploadFile, previousFile)) {
                if (!StringConstants.BASIC_IMAGE.equals(tblAttachmentDTO.getAttachmentFileAddress())) {
                    previousFileUtils.delete(previousFile);
                }
            } else {
                System.out.println("Previous file does not exist.");
            }
            // 저장한 파일 이름 데이터베이스 동기화
            tblAttachmentDTO.setAttachmentFileAddress(uploadFile.getName());
            processFileInterface.execute(tblAttachmentDTO);
        } catch (FileNotFoundException e) {
            // 이전 파일이 없을 경우 사용자에게 받은 파일 저장
            tblAttachmentDTO.setAttachmentFileAddress(uploadFile.getName());
            processFileInterface.execute(tblAttachmentDTO);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
