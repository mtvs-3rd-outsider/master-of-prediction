package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.CustomTblInquiryDTO;
import com.outsider.masterofprediction.dto.constatnt.AdminPaginationConstant;
import com.outsider.masterofprediction.service.InquiryReadService;
import com.outsider.masterofprediction.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminInquiryController {

    private final InquiryReadService inquiryReadService;

    @Autowired
    public AdminInquiryController(InquiryReadService inquiryReadService) {
        this.inquiryReadService = inquiryReadService;
    }

    @GetMapping("/admin-page/inquiry")
    public String inquiry(@RequestParam(defaultValue = "1")int page, Model model) {
        if (page < 1) page = 1;

        int totalCount = inquiryReadService.getTotalCount();
        List<CustomTblInquiryDTO> inquiry = inquiryReadService.searchNoticeLimit((page - 1) * 10, AdminPaginationConstant.itemCount);
        PaginationUtils.modelSetPaginationReturnPageNumber(model, page, totalCount);

        model.addAttribute("inquiry", inquiry);
        return "content/admin-page/inquiry/index";
    }
}
