package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.ActivityUserSubjectDTO;
import com.outsider.masterofprediction.dto.UserAttachmentDTO;
import com.outsider.masterofprediction.service.ActivityFindService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/activitypage")
public class ActivityPageController {

    private final ActivityFindService activityFindService;

    public ActivityPageController(ActivityFindService activityFindService) {
        this.activityFindService = activityFindService;
    }

    @GetMapping
    public ModelAndView getActivityPage(ModelAndView mv) {

        // mv.addObject();
        mv.setViewName("/layout/activity-page/index");
        mv.addObject("title", "Recent Activity");
        mv.addObject("view", "content/activity-page/activity-page");

        List<ActivityUserSubjectDTO> userActivity = activityFindService.findActivity();
        mv.addObject("userActivity", userActivity);

        System.out.println(userActivity);

        return mv;
    }
}
