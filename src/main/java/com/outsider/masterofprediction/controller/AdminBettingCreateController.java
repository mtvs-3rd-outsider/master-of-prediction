package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.TblSubjectDTO;
import com.outsider.masterofprediction.service.BettingCreateService;
import com.outsider.masterofprediction.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@RequestMapping("/admin-page/betting/create")
public class AdminBettingCreateController {

    private final BettingCreateService bettingCreateService;
    private final CategoryService categoryService;

    public AdminBettingCreateController(BettingCreateService bettingCreateService, CategoryService categoryService) {
        this.bettingCreateService = bettingCreateService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String createNotification(Model model){

        // model.addAttribute("betting", new BettingAndAttachmentDTO());
        model.addAttribute("categories", categoryService.findAll());
        return "content/admin-page/betting/create";
    }

    @PostMapping
    public String createBetting(@ModelAttribute TblSubjectDTO tblSubjectDTO,
                                @RequestParam("attachmentFileAddress") MultipartFile file,
                                @RequestParam("deadlineDate") Date date,
                                @RequestParam("deadlineTime") String time){

        tblSubjectDTO.setSubjectSettlementTimestamp(Timestamp.valueOf(LocalDateTime.of(LocalDate.parse(date.toString()), LocalTime.parse(time))));;
        bettingCreateService.create(tblSubjectDTO, file);
        return "redirect:/admin-page/betting";
    }
}
