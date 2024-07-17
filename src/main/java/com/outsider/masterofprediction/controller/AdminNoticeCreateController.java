package com.outsider.masterofprediction.controller;


import com.outsider.masterofprediction.dto.Notice;
import com.outsider.masterofprediction.service.NoticeCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminNoticeCreateController {

    private final NoticeCreateService noticeCreateService;

    @Autowired
    public AdminNoticeCreateController(NoticeCreateService noticeCreateService) {
        this.noticeCreateService = noticeCreateService;
    }

    @PostMapping("/admin-page/notification/create")
    public String create(@ModelAttribute Notice notice){
        noticeCreateService.creat(notice);
        return "redirect:/admin-page/notification";
    }
}
