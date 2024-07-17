package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.Notice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminNoticeRedirectCreateFormController {

    @GetMapping("/admin-page/notification/create-form")
    public String createNotification(Model model){

        model.addAttribute("notice", new Notice());
        return "content/admin-page/notification/create";
    }
}
