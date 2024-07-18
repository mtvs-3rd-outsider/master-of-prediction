package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.CustomTblInquiryDTO;
import com.outsider.masterofprediction.dto.TblInquiryReplyDTO;
import com.outsider.masterofprediction.service.InquiryReadService;
import com.outsider.masterofprediction.service.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminInquiryResponseController {

    private final InquiryReadService inquiryReadService;

    @Autowired
    public AdminInquiryResponseController(InquiryReadService inquiryReadService) {
        this.inquiryReadService = inquiryReadService;
    }

    @GetMapping("/admin-page/inquiry/{response_id}")
    public String inquiryResponse(@PathVariable Long response_id, Model model) {
        // 문의(문의아이디)가 있는지 확인해주는 서비스
        CustomTblInquiryDTO inquiryDTO = inquiryReadService.getTBLInquiryById(response_id);
        if (inquiryDTO == null || !UserSession.isAdmin()){
            return "redirect:/admin-page/inquiry";
        }


        TblInquiryReplyDTO tblInquiryReplyDTO = new TblInquiryReplyDTO();
        tblInquiryReplyDTO.setAnswerInquiryNo(response_id);

        model.addAttribute("inquiry", inquiryDTO);
        model.addAttribute("replyInquiry", tblInquiryReplyDTO);

        return "content/admin-page/inquiry/response";
    }
}
