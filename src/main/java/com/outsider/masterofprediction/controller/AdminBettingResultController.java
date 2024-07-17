package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.TblCategoryDTO;
import com.outsider.masterofprediction.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Date;

@Controller
public class AdminBettingResultController {

    private final BettingUpdateService bettingUpdateService;
    private final BettingService bettingService;
    private final CategoryService categoryService;

    @Autowired
    public AdminBettingResultController(BettingUpdateService bettingUpdateService, BettingService bettingService, CategoryService categoryService) {
        this.bettingUpdateService = bettingUpdateService;
        this.bettingService = bettingService;
        this.categoryService = categoryService;
    }

    // @PostMapping("/admin-page/betting/result/{subjectNo}")
    // public String bettingResult(@PathVariable String result, @RequestParam Long subjectNo) {
    //     // 결과 저장 로직
    //     System.out.println(result);
    //     System.out.println(subjectNo);
    //     // subject에 결과값과 시간, 설정
    //
    //     // bettingUpdateService.finish(result, subjectNo);
    //
    //     // 포인트 계산 로직
    //     // 소모된 포인트를 기준으로 비율계산하여 반환
    //
    //     return "redirect:/admin-page/betting";
    // }


    @GetMapping("/admin-page/betting/result/{subjectNo}")
    public String bettingResult(@PathVariable Long subjectNo, Model model) {
        BettingAndAttachmentDTO bettingDetail = bettingService.getBettingAndAttachmentBySubjectNo(subjectNo);
        TblCategoryDTO categoryDTO = categoryService.findByCategoryNo(bettingDetail.getSubjectCategoryNo());
        model.addAttribute("betting", bettingDetail);
        model.addAttribute("category", categoryDTO);
        model.addAttribute("deadline", new Date(bettingDetail.getSubjectSettlementTimestamp().getTime()));

        return "content/admin-page/betting/result";
    }
}
