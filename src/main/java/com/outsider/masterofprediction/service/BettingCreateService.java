package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.TblAttachmentDTO;
import com.outsider.masterofprediction.dto.TblSubjectDTO;
import com.outsider.masterofprediction.dto.constatnt.SubjectStatus;
import com.outsider.masterofprediction.mapper.AttachmentMapper;
import com.outsider.masterofprediction.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@Service
public class BettingCreateService {
    private final SubjectMapper subjectMapper;
    private final AttachmentMapper attachmentMapper;
    private final FileStorageService fileStorageService;
    @Value("${default_image}")
    private String defaultImage;


    @Autowired
    public BettingCreateService(SubjectMapper subjectMapper, AttachmentMapper attachmentMapper,
                                FileStorageService fileStorageService) {
        this.subjectMapper = subjectMapper;
        this.attachmentMapper = attachmentMapper;
        this.fileStorageService = fileStorageService;
    }

    @Transactional
    public void create(TblSubjectDTO tblSubjectDTO, MultipartFile file){
        
        long userId = UserSession.getUserId();

        tblSubjectDTO.setSubjectRegisterUserNo(userId);
        // 임시 데이터
        tblSubjectDTO.setSubjectCategoryNo(tblSubjectDTO.getSubjectCategoryNo());
        tblSubjectDTO.setSubjectStatus(SubjectStatus.IN_PROGRESS.getValue());
        subjectMapper.insertSubject(tblSubjectDTO);

        TblAttachmentDTO tblAttachmentDTO = new TblAttachmentDTO();
        tblAttachmentDTO.setAttachmentRegistUserNo(userId);
        tblAttachmentDTO.setSubjectNo(tblSubjectDTO.getSubjectNo());

        // 이미지 파일이 없을 경우
        if ("".equals(file.getOriginalFilename())) {
            tblAttachmentDTO.setAttachmentFileAddress(defaultImage);
            attachmentMapper.setAttachmentsBySubjectNo(tblAttachmentDTO);
            return;
        }


        // 이미지 파일이 있을 경우
        File uploadFile = fileStorageService.storeFile(file);
        tblAttachmentDTO.setAttachmentFileAddress(uploadFile.getName());
        attachmentMapper.setAttachmentsBySubjectNo(tblAttachmentDTO);
        // processFileService.execute(tblAttachmentDTO, file, attachmentMapper::setAttachmentsBySubjectNo);
    }
}
