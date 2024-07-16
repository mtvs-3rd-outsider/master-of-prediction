package com.outsider.masterofprediction.controller.activitypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/activitypage")
public class ActivityPageController {

    @GetMapping()
    public ModelAndView getActivityPage(ModelAndView mv){

        // mv.addObject();
        mv.setViewName("/layout/activity-page/index");
        mv.addObject("title", "Recent Activity");
        mv.addObject("view", "content/activity-page/activity-page");

        return mv;
    }
}
