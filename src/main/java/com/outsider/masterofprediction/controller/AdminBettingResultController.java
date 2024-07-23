package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.response.BettingAndAttachmentDTO;
import com.outsider.masterofprediction.service.BettingResultService;
import com.outsider.masterofprediction.service.BettingService;
import com.outsider.masterofprediction.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Controller
public class AdminBettingResultController {
    @Value("${file.imgUrl}")
    private String imgUrl;
    private final BettingService bettingService;
    private final CategoryService categoryService;
    private final BettingResultService bettingResultService;

    @Autowired
    public AdminBettingResultController(BettingService bettingService, CategoryService categoryService, BettingResultService bettingResultService) {
        this.bettingService = bettingService;
        this.categoryService = categoryService;
        this.bettingResultService = bettingResultService;
    }

    @PostMapping("/admin-page/betting/result/{subjectNo}")
    public String bettingResult(@PathVariable Long subjectNo, @RequestParam String result) {
        bettingResultService.updateBettingResult(subjectNo, result);
        return "redirect:/admin-page/betting";
    }


    @GetMapping("/admin-page/betting/result/{subjectNo}")
    public String bettingResult(@PathVariable Long subjectNo, Model model) {
        BettingAndAttachmentDTO betting = bettingService.getBettingAndAttachmentBySubjectNo(subjectNo);

        betting.setAttachmentFileAddress(Paths.get(imgUrl).resolve(betting.getAttachmentFileAddress()).toString());

        ZonedDateTime zonedDateTime = betting.getSubjectSettlementTimestamp().toInstant().atZone(ZoneId.systemDefault());


        model.addAttribute("betting", betting);
        model.addAttribute("deadlineDate", zonedDateTime.toLocalDate());
        model.addAttribute("deadlineTime", zonedDateTime.toLocalTime());
        model.addAttribute("categories", categoryService.findAll());

        return "content/admin-page/betting/result";
    }
}
