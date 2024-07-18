package com.outsider.masterofprediction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminLoginController {
    @GetMapping("/admin-page/login")
    public String login() {
        return "content/admin-page/login/index";
    }
}
