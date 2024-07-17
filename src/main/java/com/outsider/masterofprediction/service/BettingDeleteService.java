package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.mapper.AttachmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BettingDeleteService {
    private final SubjectMapper subjectMapper;
    private final AttachmentMapper attachmentMapper;

    @Autowired
    public BettingDeleteService(SubjectMapper subjectMapper, AttachmentMapper attachmentMapper) {
        this.subjectMapper = subjectMapper;
        this.attachmentMapper = attachmentMapper;
    }

    @Transactional
    public void delete(Long subjectNo, Long attachmentNo){
        this.attachmentMapper.deleteAttachmentsBySubjectNo(subjectNo);
        this.subjectMapper.deleteSubject(subjectNo);
    }

}
