package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.BettingAddDTO;
import com.outsider.masterofprediction.dto.TblCategoryDTO;
import com.outsider.masterofprediction.service.BettingAddService;
import com.outsider.masterofprediction.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/bettingaddpage")
public class BettingAddPageController {

    private final BettingAddService bettingAddService;
    private final CategoryService categoryService;

    public BettingAddPageController(BettingAddService bettingAddService, CategoryService categoryService) {
        this.bettingAddService = bettingAddService;
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ModelAndView getBettingAddPage(ModelAndView mv) {
        System.out.println("TEST");
        List<TblCategoryDTO> categoryList = categoryService.findAll();
        mv.setViewName("/layout/betting-page/add");
        mv.addObject("title", "Add Betting Page");
        mv.addObject("categoryList", categoryList);
        return mv;
    }


//    @PostMapping
//    public String createBetting(@ModelAttribute TblSubjectDTO tblSubjectDTO,
//                                @RequestParam("attachmentFileAddress") MultipartFile     file,
//                                @RequestParam("deadline") Date date){
//        tblSubjectDTO.setSubjectSettlementTimestamp(new Timestamp(date.getTime()));
//        bettingCreateService.create(tblSubjectDTO, file);
//        return "redirect:/admin-page/betting";
//    }

    @PostMapping("/create")
    public String createBettingGame(@ModelAttribute BettingAddDTO bettingAddDTO
                                    ,@RequestParam("attachmentFileAddress")MultipartFile file
//                                    ,@RequestParam("date")Date data,
//                                    ,@RequestParam("time")Time
    )
    {

        System.out.println(bettingAddDTO);
        System.out.println(file);
//        bettingAddDTO.setSubjectSettlementTimestamp(new TimeStamp);
//        System.out.println(file);
//        bettingAddService.create(bettingAddDTO);
        return "redirect:/mainpage";
    }


}
