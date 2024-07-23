package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.ActivityUserSubjectDTO;
import com.outsider.masterofprediction.dto.CustomUserDetail;
import com.outsider.masterofprediction.dto.constatnt.IntConstants;
import com.outsider.masterofprediction.dto.response.MainPageSubjectDTO;
import com.outsider.masterofprediction.mapper.BettingOrderMapper;
import com.outsider.masterofprediction.service.CategoryService;
import com.outsider.masterofprediction.service.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainPageController {

    private final MainPageService mainPageService;
    private final CategoryService categoryService;
    private final BettingOrderMapper bettingOrderMapper;

    @Autowired
    public MainPageController(MainPageService mainPageService, CategoryService categoryService, BettingOrderMapper bettingOrderMapper) {
        this.mainPageService = mainPageService;
        this.categoryService = categoryService;
        this.bettingOrderMapper = bettingOrderMapper;
    }

    @GetMapping()
    public ModelAndView getMainPage(ModelAndView mv,@AuthenticationPrincipal CustomUserDetail user){

        // mv.addObject();
        List<MainPageSubjectDTO> mainPageSubjectDTOS =  mainPageService.findMainPageSubjectList();

        List<ActivityUserSubjectDTO> activityUserSubjectDTO =
                bettingOrderMapper.findActivityCount(IntConstants.MAIN_PAGE_ACTIVITY_COUNT);

        mv.setViewName("/layout/main-page/index");
        mv.addObject("subjects", mainPageSubjectDTOS);
        mv.addObject("categories", categoryService.findAll());
        mv.addObject("title", "Master Of Prediction");
        mv.addObject("activity", activityUserSubjectDTO);
        mv.addObject("view", "content/main-page/main-page");
        mv.addObject("Point",user.getPoint());
        return mv;
    }
}