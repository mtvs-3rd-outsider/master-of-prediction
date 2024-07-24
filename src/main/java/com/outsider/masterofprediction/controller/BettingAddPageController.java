package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.CustomUserDetail;
import com.outsider.masterofprediction.dto.TblCategoryDTO;
import com.outsider.masterofprediction.dto.TblSubjectDTO;
import com.outsider.masterofprediction.service.BettingCreateService;
import com.outsider.masterofprediction.service.CategoryService;
import com.outsider.masterofprediction.service.UserManagementService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/bettingaddpage")
public class BettingAddPageController {

    private final BettingCreateService bettingCreateService;
    private final CategoryService categoryService;
    private final UserManagementService userManagementService;

    public BettingAddPageController(BettingCreateService bettingCreateService, CategoryService categoryService, UserManagementService userManagementService) {
        this.bettingCreateService = bettingCreateService;
        this.categoryService = categoryService;
        this.userManagementService = userManagementService;
    }

    @GetMapping()
    public ModelAndView getBettingAddPage(ModelAndView mv ,@AuthenticationPrincipal CustomUserDetail userPrincipal) {
        if(userPrincipal ==null)
        {
            mv.setViewName("redirect:/");
        }
        List<TblCategoryDTO> categoryList = categoryService.findAll();
        mv.setViewName("/layout/index");
        mv.addObject("title", "Add Betting Page");
        mv.addObject("view", "fragments/betting-add");
        mv.addObject("categoryList", categoryList);
        return mv;
    }


    @PostMapping("/create")
    public String createBetting(@ModelAttribute TblSubjectDTO tblSubjectDTO,
                                @RequestParam("attachmentFileAddress") MultipartFile file,
                                @RequestParam("deadlineDate") Date date,
                                @RequestParam("deadlineTime") String time){
        tblSubjectDTO.setSubjectSettlementTimestamp(Timestamp.valueOf(LocalDateTime.of(LocalDate.parse(date.toString()), LocalTime.parse(time))));;
        bettingCreateService.create(tblSubjectDTO, file);
        return "redirect:/";
    }

}
