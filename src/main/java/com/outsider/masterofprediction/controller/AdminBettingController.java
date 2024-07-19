package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.constatnt.AdminPaginationConstant;
import com.outsider.masterofprediction.dto.response.BettingAndAttachmentDTO;
import com.outsider.masterofprediction.service.BettingService;
import com.outsider.masterofprediction.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminBettingController {

    private final BettingService bettingService;

    @Autowired
    public AdminBettingController(BettingService bettingService) {
        this.bettingService = bettingService;
    }
    @GetMapping("/admin-page/betting")
    public String betting(@RequestParam(defaultValue = "1")int page, Model model) {
        if (page < 1) page = 1;

        List<BettingAndAttachmentDTO> bettingAndAttachmentDTOList =
                bettingService.getSubjectsJoinAttachmentsLimit((page - 1) * 10, AdminPaginationConstant.itemCount );
        int totalCount = bettingService.getTotalCount();
        PaginationUtils.modelSetPaginationReturnPageNumber(model, page, totalCount);
        model.addAttribute("bettingItems", bettingAndAttachmentDTOList);
        return "content/admin-page/betting/index";
    }
}
