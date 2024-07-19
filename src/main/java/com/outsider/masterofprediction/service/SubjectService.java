package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.TblSubjectDTO;
import com.outsider.masterofprediction.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectMapper subjectMapper;

    @Autowired
    public SubjectService(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    public TblSubjectDTO getSubjectBySubjectNo(long subjectNo) {
        return subjectMapper.getSubjectBySubjectNo(subjectNo);
    }
}
