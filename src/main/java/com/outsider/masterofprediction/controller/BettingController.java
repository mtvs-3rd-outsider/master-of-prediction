package com.outsider.masterofprediction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BettingController {

    @GetMapping("/betting")
    public String getBettingPage() {
        return "/content/betting-page/betting-page";
    }
}
