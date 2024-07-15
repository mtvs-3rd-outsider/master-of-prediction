package com.outsider.masterofprediction.controller.mainpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mainpage")
public class MainPageController {

    @GetMapping()
    public ModelAndView getMainPage(ModelAndView mv){

        // mv.addObject();
        mv.setViewName("/layout/main-page/index");
        mv.addObject("title", "Main Page");
        mv.addObject("view", "content/main-page/main-page");

        return mv;
    }
}