package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.Notice;
import com.outsider.masterofprediction.dto.constatnt.AdminPaginationConstant;
import com.outsider.masterofprediction.service.UserNoticeService;
import com.outsider.masterofprediction.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/notification")
public class UserNoticeController {

    private final UserNoticeService userNoticeService;

    @Autowired
    public UserNoticeController(UserNoticeService userNoticeService) {
        this.userNoticeService = userNoticeService;
    }

    @GetMapping()
    public String notification(@RequestParam(defaultValue = "1") int page, Model model) {
        if (page < 1) page = 1;

        int totalCount = userNoticeService.totalCount();
        List<Notice> noticeList = userNoticeService.searchNoticeLimit((page - 1) * 10, AdminPaginationConstant.itemCount);
        PaginationUtils.modelSetPaginationReturnPageNumber(model, page, totalCount);
        model.addAttribute("noticeList", noticeList);
        if (noticeList.size() == 0) {
            model.addAttribute("view", "content/notice-page/no-data");
        } else {
            model.addAttribute("view", "content/notice-page/index");
        }
        return "/layout/main-page/index";
    }

    @GetMapping("/{notification_id}")
    public String adminNotificationUpdatePage(@PathVariable Long notification_id, Model model) {
        Notice notice = userNoticeService.noticeById(notification_id);
        model.addAttribute("view", "content/notice-page/detail");
        model.addAttribute("notice", notice);
        return "/layout/main-page/index";
    }
}