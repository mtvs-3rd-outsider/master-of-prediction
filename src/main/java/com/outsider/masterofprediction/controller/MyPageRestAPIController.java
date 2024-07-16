package com.outsider.masterofprediction.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
@RequestMapping("/api")
public class MyPageRestAPIController {

    @GetMapping("/purchase-history/{page}")
    public ModelAndView getPurchaseHistory(@PathVariable int page, ModelAndView mv) {

        return mv;
    }
    @GetMapping("/comments/{page}")
    public ModelAndView comment(@PathVariable int page, ModelAndView mv) {

        return mv;
    }
    @GetMapping("inquirys/{page}")
    public ModelAndView getInquirys(@PathVariable int page, ModelAndView mv) {

        return mv;
    }
}
