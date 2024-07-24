package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.TblAttachmentDTO;
import com.outsider.masterofprediction.dto.TblSubjectDTO;
import com.outsider.masterofprediction.dto.constatnt.SubjectStatus;
import com.outsider.masterofprediction.mapper.AttachmentMapper;
import com.outsider.masterofprediction.mapper.SubjectMapper;
import com.outsider.masterofprediction.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.time.LocalDateTime;


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
        if (userId == 0) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }

        Validation.subject(tblSubjectDTO);
        tblSubjectDTO.setSubjectRegisterUserNo(userId);
        tblSubjectDTO.setSubjectStatus(SubjectStatus.IN_PROGRESS.getValue());
        try {
            subjectMapper.insertSubject(tblSubjectDTO);
            TblAttachmentDTO tblAttachmentDTO = new TblAttachmentDTO();
            tblAttachmentDTO.setAttachmentRegistUserNo(userId);
            tblAttachmentDTO.setSubjectNo(tblSubjectDTO.getSubjectNo());

            // 사용자가 이미지를 안넣었을 경우 기본이미지 부여
            if ("".equals(file.getOriginalFilename())) {
                tblAttachmentDTO.setAttachmentFileAddress(defaultImage);
                attachmentMapper.setAttachmentsBySubjectNo(tblAttachmentDTO);
                return;
            }
            // 사용자가 이미지 파일을 넣을 경우
            File uploadFile = fileStorageService.storeFile(file);
            tblAttachmentDTO.setAttachmentFileAddress(uploadFile.getName());
            attachmentMapper.setAttachmentsBySubjectNo(tblAttachmentDTO);
        }catch (IllegalStateException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data provided");
        }
    }

    private void validation(TblSubjectDTO tblSubjectDTO){
        if (tblSubjectDTO.getSubjectTitle().isBlank() ||
                tblSubjectDTO.getSubjectCategoryNo() == 0 ||
            tblSubjectDTO.getSubjectSettlementTimestamp() == null ||
                LocalDateTime.now().isAfter(tblSubjectDTO.getSubjectSettlementTimestamp().toLocalDateTime())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data provided");
        }
    }
}
