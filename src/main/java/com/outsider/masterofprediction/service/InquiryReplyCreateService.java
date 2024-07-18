package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.TblInquiryReplyDTO;
import com.outsider.masterofprediction.mapper.InquiryReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class InquiryReplyCreateService {

    private final InquiryReplyMapper inquiryReplyMapper;

    @Autowired
    public InquiryReplyCreateService(InquiryReplyMapper inquiryReplyMapper) {
        this.inquiryReplyMapper = inquiryReplyMapper;
    }

    public void createInquiry(TblInquiryReplyDTO reply) {
        inquiryReplyMapper.insertInquiry(reply);
    }
}
