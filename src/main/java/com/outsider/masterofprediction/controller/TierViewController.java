package com.outsider.masterofprediction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TierViewController {

    @GetMapping("/tier")
    public ModelAndView view(ModelAndView mv){
        mv.setViewName("layout/index");
        mv.addObject("title", "tier");
        mv.addObject("view", "content/tier/tier");

        return mv;
    }
}
