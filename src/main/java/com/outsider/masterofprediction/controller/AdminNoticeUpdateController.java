package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.Notice;
import com.outsider.masterofprediction.service.NoticeUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;

@Controller
public class AdminNoticeUpdateController {

    private final NoticeUpdateService noticeUpdateService;

    @Autowired
    public AdminNoticeUpdateController(NoticeUpdateService noticeUpdateService) {
        this.noticeUpdateService = noticeUpdateService;
    }


    @PostMapping("/admin-page/notification/update/{id}")
    public String adminNotificationUpdateSend(@ModelAttribute Notice notice, Model model) {
        notice.setTimestamp(new Timestamp(System.currentTimeMillis()));
        int result = noticeUpdateService.noticeDetailUpdate(notice);
//        result 값이 0일경우 실패처리
        return "redirect:/admin-page/notification";
    }
}
