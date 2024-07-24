package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.service.NoticeDeleteByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminNoticeDeleteByIdController {

    private final NoticeDeleteByIdService noticeDeleteByIdService;

    @Autowired
    public AdminNoticeDeleteByIdController(NoticeDeleteByIdService noticeDeleteByIdService) {
        this.noticeDeleteByIdService = noticeDeleteByIdService;
    }

    @PostMapping("/admin-page/notification/delete")
    public String notificationDeleteById(@RequestParam("ids") List<Long> ids){
        noticeDeleteByIdService.deleteByIds(ids);

        return "redirect:/admin-page/notification";
    }

}
