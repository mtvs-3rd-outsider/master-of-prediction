package com.outsider.masterofprediction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TierViewController {

    @GetMapping("/tier")
    public String view(){
        return "content/tier/tier";
    }
}
