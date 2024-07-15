package com.outsider.masterofprediction.controller.rankPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/rankpage")
public class RankPageController {

    @GetMapping()
    public ModelAndView getRankPage(ModelAndView mv){

        // mv.addObject();
        mv.setViewName("/layout/rank-page/index");
        mv.addObject("title", "User Ranking");
        mv.addObject("view", "content/rank-page/rank-page");

        return mv;
    }
}
