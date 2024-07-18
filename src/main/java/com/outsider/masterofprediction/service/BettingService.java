package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.TblAttachmentDTO;
import com.outsider.masterofprediction.dto.TblSubjectDTO;
import com.outsider.masterofprediction.dto.response.BettingAndAttachmentDTO;
import com.outsider.masterofprediction.mapper.AttachmentMapper;
import com.outsider.masterofprediction.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BettingService {

    private final SubjectMapper subjectMapper;
    private final AttachmentMapper attachmentMapper;

    @Autowired
    public BettingService(SubjectMapper subjectMapper, AttachmentMapper attachmentMapper) {
        this.subjectMapper = subjectMapper;
        this.attachmentMapper = attachmentMapper;
    }

    public List<BettingAndAttachmentDTO> getSubjectsJoinAttachments(){
        return this.subjectMapper.getSubjectsJoinAttachments();
    }

    public List<BettingAndAttachmentDTO> getSubjectsJoinAttachmentsLimit(int first, int count){
        return this.subjectMapper.getSubjectsJoinAttachmentsLimit(first, count);
    }

    public int getTotalCount(){
        return this.subjectMapper.totalCount();
    }

    public BettingAndAttachmentDTO getBettingAndAttachmentBySubjectNo(Long subjectNo){
        return this.subjectMapper.getSubjectJoinAttachmentBySubjectNo(subjectNo);
    }

    /**
     * 상품번호를 받아서 상품정보와 이미지를 받아옵니다.
     * map.get("subject")
     * map.get("attachment")
     * @param subjectNo 상품번호
     * @return Map<String, Object> | null
     */
    public Map<String, Object> getItemBySubjectNo(Long subjectNo) {
        Map<String, Object> map = new HashMap<>();
        TblSubjectDTO tblSubjectDTO = this.subjectMapper.getSubjectById(subjectNo);
        if (tblSubjectDTO != null) {
            TblAttachmentDTO tblAttachmentDTO = this.attachmentMapper.getAttachmentsBySubjectNo(subjectNo);
            // 사진이 없을경우 기본 사진값넣기
            if (tblAttachmentDTO.getAttachmentFileAddress() == null){
                tblAttachmentDTO.setAttachmentFileAddress("images/logo.png");
            }
            map.put("subject", tblSubjectDTO);
            map.put("attachment", tblAttachmentDTO);
            return map;
        }
        return null;
    }

}
