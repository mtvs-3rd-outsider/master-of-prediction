package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.response.MainPageSubjectDTO;
import com.outsider.masterofprediction.service.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainPageController {

    private final MainPageService mainPageService;

    @Autowired
    public MainPageController(MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }

    @GetMapping()
    public ModelAndView getMainPage(ModelAndView mv){

        // mv.addObject();
        List<MainPageSubjectDTO> mainPageSubjectDTOS =  mainPageService.findMainPageSubjectList();
        System.out.println(mainPageSubjectDTOS);

        mv.setViewName("/layout/main-page/index");
        mv.addObject("subjects", mainPageSubjectDTOS);
        mv.addObject("title", "Master Of Prediction");
        mv.addObject("view", "content/main-page/main-page");

        return mv;
    }
}