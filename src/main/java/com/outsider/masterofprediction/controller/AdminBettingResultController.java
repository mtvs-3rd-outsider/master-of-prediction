package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.TblCategoryDTO;
import com.outsider.masterofprediction.dto.response.BettingAndAttachmentDTO;
import com.outsider.masterofprediction.service.BettingService;
import com.outsider.masterofprediction.service.BettingUpdateService;
import com.outsider.masterofprediction.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Paths;
import java.sql.Date;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Controller
public class AdminBettingResultController {
    @Value("${file.imgUrl}")
    private String imgUrl;
    private final BettingUpdateService bettingUpdateService;
    private final BettingService bettingService;
    private final CategoryService categoryService;

    @Autowired
    public AdminBettingResultController(BettingUpdateService bettingUpdateService, BettingService bettingService, CategoryService categoryService) {
        this.bettingUpdateService = bettingUpdateService;
        this.bettingService = bettingService;
        this.categoryService = categoryService;
    }

    @PostMapping("/admin-page/betting/result/{subjectNo}")
    public String bettingResult(@PathVariable Long subjectNo, @RequestParam String result) {
        System.out.println(subjectNo);
        System.out.println(result);


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
