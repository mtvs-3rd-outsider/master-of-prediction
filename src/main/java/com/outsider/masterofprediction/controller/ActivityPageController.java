package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.ActivityUserSubjectDTO;
import com.outsider.masterofprediction.dto.CustomUserDetail;
import com.outsider.masterofprediction.dto.UserAttachmentDTO;
import com.outsider.masterofprediction.mapper.BettingAddMapper;
import com.outsider.masterofprediction.service.ActivityFindService;
import com.outsider.masterofprediction.service.CategoryService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ModelAndView getActivityPage(ModelAndView mv,@AuthenticationPrincipal CustomUserDetail user) {

        // mv.addObject();
        mv.setViewName("/layout/activity-page/index");
        mv.addObject("title", "Recent Activity");
        mv.addObject("view", "content/activity-page/activity-page");
        mv.addObject("categories", categoryService.findAll());

        List<ActivityUserSubjectDTO> userActivity = activityFindService.findActivity();
        mv.addObject("userActivity", userActivity);
        mv.addObject("Point",user.getPoint());
        System.out.println(userActivity);

        return mv;
    }
}
