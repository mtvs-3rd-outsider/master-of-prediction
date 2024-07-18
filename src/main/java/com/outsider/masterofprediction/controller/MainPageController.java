package com.outsider.masterofprediction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainPageController {

    @GetMapping()
    public ModelAndView getMainPage(ModelAndView mv){

        // mv.addObject();
        mv.setViewName("/layout/main-page/index");
        mv.addObject("title", "Main Page");
        mv.addObject("view", "content/main-page/test");

        return mv;
    }
}