package com.outsider.masterofprediction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminHomeController {
    @GetMapping("/admin-page")
    public String showAdminPage(){
        return "content/admin-page/index";
    }
}
