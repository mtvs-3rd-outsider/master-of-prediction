package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.TblSubjectDTO;
import com.outsider.masterofprediction.dto.response.BettingAndAttachmentDTO;
import com.outsider.masterofprediction.service.BettingService;
import com.outsider.masterofprediction.service.BettingUpdateService;
import com.outsider.masterofprediction.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;

@Controller
public class AdminBettingUpdateController {
    @Value("${file.imgUrl}")
    private String imgUrl;
    private final BettingService bettingService;
    private final BettingUpdateService bettingUpdateService;
    private final CategoryService categoryService;

    @Autowired
    public AdminBettingUpdateController(BettingUpdateService bettingUpdateService,
                                        BettingService bettingService,
                                        CategoryService categoryService) {
        this.bettingUpdateService = bettingUpdateService;
        this.bettingService = bettingService;
        this.categoryService = categoryService;
    }

    @PostMapping("/admin-page/betting/update/{subjectNo}")
    public String adminBettingUpdateSend(@ModelAttribute TblSubjectDTO tblSubjectDTO,
                                         @RequestParam("attachmentFileAddress") MultipartFile file,
                                         @RequestParam("deadline") Date date) {
        try {
            bettingUpdateService.update(tblSubjectDTO, file, date);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin-page/betting";
    }

    @GetMapping("/admin-page/betting/{subjectNo}")
    public String adminNotificationUpdatePage(@PathVariable Long subjectNo, Model model) {
        BettingAndAttachmentDTO betting = bettingService.getBettingAndAttachmentBySubjectNo(subjectNo);

        betting.setAttachmentFileAddress(Paths.get(imgUrl).resolve(betting.getAttachmentFileAddress()).toString());



        model.addAttribute("deadline", new Date(betting.getSubjectSettlementTimestamp().getTime()));
        model.addAttribute("betting", betting);
        model.addAttribute("categories", categoryService.findAll());

        return "content/admin-page/betting/update";
    }

}

