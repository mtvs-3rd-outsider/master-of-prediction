package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.TblInquiryReplyDTO;
import com.outsider.masterofprediction.dto.constatnt.InquiryReplyStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InquiryAndReplyResit {

    private final InquiryReplyCreateService inquiryReplyCreateService;
    private final InquiryStatusUpdateService inquiryStatusUpdateService;

    @Autowired
    public InquiryAndReplyResit(InquiryReplyCreateService inquiryReplyCreateService, InquiryStatusUpdateService inquiryStatusUpdateService) {
        this.inquiryReplyCreateService = inquiryReplyCreateService;
        this.inquiryStatusUpdateService = inquiryStatusUpdateService;
    }

    @Transactional
    public void resit(InquiryReplyStatus inquiryReplyStatus, TblInquiryReplyDTO reply) {
        inquiryStatusUpdateService.update(inquiryReplyStatus, reply.getAnswerInquiryNo());
        inquiryReplyCreateService.createInquiry(reply);
    }



}
