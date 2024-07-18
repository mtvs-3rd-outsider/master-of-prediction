package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.TblInquiryReplyDTO;
import com.outsider.masterofprediction.dto.constatnt.InquiryReplyStatus;
import com.outsider.masterofprediction.service.InquiryAndReplyResit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminInquiryReplyController {

    private final InquiryAndReplyResit inquiryAndReplyResit;

    @Autowired
    public AdminInquiryReplyController(InquiryAndReplyResit inquiryAndReplyResit) {
        this.inquiryAndReplyResit = inquiryAndReplyResit;
    }


    @PostMapping("/admin-page/inquiry/result")
    public String result(@ModelAttribute TblInquiryReplyDTO reply){
        inquiryAndReplyResit.resit(InquiryReplyStatus.YES, reply);
        return "redirect:/admin-page/inquiry";
    }

}

