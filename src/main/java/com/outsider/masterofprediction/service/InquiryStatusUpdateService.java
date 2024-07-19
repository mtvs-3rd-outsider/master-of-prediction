package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.constatnt.InquiryReplyStatus;
import com.outsider.masterofprediction.mapper.InquiryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InquiryStatusUpdateService {

    private final InquiryMapper inquiryMapper;

    @Autowired
    public InquiryStatusUpdateService(InquiryMapper inquiryMapper) {
        this.inquiryMapper = inquiryMapper;
    }


    public void update(InquiryReplyStatus inquiryReplyStatus, Long id){
        inquiryMapper.updateState(inquiryReplyStatus, id);
    }
}
