package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.ActivityUserSubjectDTO;
import com.outsider.masterofprediction.dto.CustomUserDetail;
import com.outsider.masterofprediction.dto.UserAttachmentDTO;
import com.outsider.masterofprediction.mapper.BettingAddMapper;
import com.outsider.masterofprediction.service.ActivityFindService;
import com.outsider.masterofprediction.service.CategoryService;
import com.outsider.masterofprediction.service.UserManagementService;
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
    private final UserManagementService userManagementService;

    public ActivityPageController(ActivityFindService activityFindService, CategoryService categoryService, UserManagementService userManagementService) {
        this.activityFindService = activityFindService;
        this.categoryService = categoryService;
        this.userManagementService = userManagementService;
    }

    @GetMapping
    public ModelAndView getActivityPage(ModelAndView mv) {

        // mv.addObject();
        mv.setViewName("/layout/activity-page/index");
        mv.addObject("title", "Recent Activity");
        mv.addObject("view", "content/activity-page/activity-page");
        mv.addObject("categories", categoryService.findAll());

        List<ActivityUserSubjectDTO> userActivity = activityFindService.findActivity();
        mv.addObject("userActivity", userActivity);
        
        System.out.println(userActivity);

        return mv;
    }
}
