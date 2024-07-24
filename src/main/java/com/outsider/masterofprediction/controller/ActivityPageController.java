package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.ActivityUserSubjectDTO;
import com.outsider.masterofprediction.service.ActivityFindService;
import com.outsider.masterofprediction.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/activitypage")
public class ActivityPageController {

    private final ActivityFindService activityFindService;
    private final CategoryService categoryService;

    public ActivityPageController(ActivityFindService activityFindService, CategoryService categoryService) {
        this.activityFindService = activityFindService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ModelAndView getActivityPage(ModelAndView mv) {

        mv.setViewName("/layout/index");
        mv.addObject("title", "Recent Activity");
        mv.addObject("view", "content/activity-page");
        List<ActivityUserSubjectDTO> userActivity = activityFindService.findActivity();
        mv.addObject("userActivity", userActivity);

        return mv;
    }
}
