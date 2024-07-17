package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.Notice;
import com.outsider.masterofprediction.dto.constatnt.AdminPaginationConstant;
import com.outsider.masterofprediction.service.NoticeService;
import com.outsider.masterofprediction.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminNoticeController {
    private final NoticeService noticeService;

    @Autowired
    public AdminNoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }


    @GetMapping("/admin-page/notification")
    public String notification(@RequestParam(defaultValue = "1")int page, Model model) {
        if (page < 1) page = 1;

        int totalCount = noticeService.totalCount();
        List<Notice> notices = noticeService.searchNoticeLimit((page - 1) * 10, AdminPaginationConstant.itemCount);
        PaginationUtils.modelSetPaginationReturnPageNumber(model, page, totalCount);
        model.addAttribute("notices", notices);
        return "content/admin-page/notification/index";
    }

}
