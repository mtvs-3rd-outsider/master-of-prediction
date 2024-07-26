package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.ActivityUserSubjectDTO;
import com.outsider.masterofprediction.dto.constatnt.IntConstants;
import com.outsider.masterofprediction.mapper.BettingOrderMapper;
import com.outsider.masterofprediction.service.ActivityFindService;
import com.outsider.masterofprediction.service.CategoryService;
import com.outsider.masterofprediction.utils.ConvertImageUrl;
import com.outsider.masterofprediction.utils.FileUtil;
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
    private final BettingOrderMapper bettingOrderMapper;

    public ActivityPageController(ActivityFindService activityFindService, CategoryService categoryService, BettingOrderMapper bettingOrderMapper) {
        this.activityFindService = activityFindService;
        this.categoryService = categoryService;
        this.bettingOrderMapper = bettingOrderMapper;
    }

    @GetMapping
    public ModelAndView getActivityPage(ModelAndView mv) {
        List<ActivityUserSubjectDTO> activityUserSubjectDTO =
                bettingOrderMapper.findActivityCount(IntConstants.MAIN_PAGE_ACTIVITY_COUNT);

        for (ActivityUserSubjectDTO tblAttachmentDTO: activityUserSubjectDTO) {
            tblAttachmentDTO.getAttachmentUser().setAttachmentFileAddress(FileUtil.checkFileOrigin(tblAttachmentDTO.getAttachmentUser().getAttachmentFileAddress()));
            tblAttachmentDTO.getAttachmentSubject().setAttachmentFileAddress(FileUtil.checkFileOrigin(tblAttachmentDTO.getAttachmentSubject().getAttachmentFileAddress()));
        }
        mv.setViewName("/layout/index");
        mv.addObject("title", "Recent Activity");
        mv.addObject("activity", activityUserSubjectDTO);
        mv.addObject("view", "fragments/activity-content");

        return mv;
    }
}
