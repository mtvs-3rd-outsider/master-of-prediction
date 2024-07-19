package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.Notice;
import com.outsider.masterofprediction.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminNoticeDetailController {
    private final NoticeService noticeService;

    @Autowired
    public AdminNoticeDetailController(NoticeService noticeService1) {
        this.noticeService = noticeService1;
    }

    @GetMapping("/admin-page/notification/{notification_id}")
    public String adminNotificationUpdatePage(@PathVariable Long notification_id, Model model) {
        Notice notice = noticeService.noticeById(notification_id);
        model.addAttribute(notice);
        return "content/admin-page/notification/update";
    }


}
