package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.TblAttachmentDTO;
import com.outsider.masterofprediction.dto.TblSubjectDTO;
import com.outsider.masterofprediction.dto.constatnt.StringConstants;
import com.outsider.masterofprediction.mapper.AttachmentMapper;
import com.outsider.masterofprediction.mapper.SubjectMapper;
import com.outsider.masterofprediction.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

@Service
public class BettingUpdateService {
    private final SubjectMapper subjectMapper;
    private final AttachmentMapper attachmentMapper;
    private final ProcessFileService processFileService;

    @Autowired
    public BettingUpdateService(SubjectMapper subjectMapper, AttachmentMapper attachmentMapper, ProcessFileService processFileService) {
        this.subjectMapper = subjectMapper;
        this.attachmentMapper = attachmentMapper;
        this.processFileService = processFileService;
    }

    @Transactional
    public void update(TblSubjectDTO tblSubjectDTO, MultipartFile file, Date date) throws IOException {

        tblSubjectDTO.setSubjectSettlementTimestamp(new Timestamp(date.getTime()));
        Validation.subject(tblSubjectDTO);
        if (!file.isEmpty()){
            TblAttachmentDTO tblAttachmentDTO = this.attachmentMapper.getAttachmentsBySubjectNo(tblSubjectDTO.getSubjectNo());
            if (StringConstants.BASIC_IMAGE.equals(file.getOriginalFilename())
                    || tblAttachmentDTO.getAttachmentFileAddress().equals(file.getOriginalFilename())){

                subjectMapper.updateSubjectTitleAndCategoryAndSettlementTimestamp(tblSubjectDTO);
                return;
            }
            // Transactional 상황을 고려해서 예외처리를 해야함
            processFileService.execute(tblAttachmentDTO, file, attachmentMapper::updateAttachmentBySubjectNo);
            subjectMapper.updateSubjectTitleAndCategoryAndSettlementTimestamp(tblSubjectDTO);
        }
    }

    public void finish(String result, Long subjectNo){
        String exitStatus = "종료";

        if (UserSession.isAdmin() && (result.equals("Yes") || result.equals("No"))) {
            TblSubjectDTO tblSubjectDTO = new TblSubjectDTO();
            tblSubjectDTO.setSubjectNo(subjectNo);
            tblSubjectDTO.setSubjectFinishResult(result);
            tblSubjectDTO.setSubjectStatus(exitStatus);
            
            tblSubjectDTO.setSubjectFinishTimestamp(new Timestamp(System.currentTimeMillis()));
            subjectMapper.updateSubjectResult(tblSubjectDTO);
        //     포인트 정산 기능 추가
        //     가입한 유저들의 주문내역을 가져와서
        //      내가 건 금액 * (전체금액 / 내가 건 쪽 전체금액)
        }
    }
}
