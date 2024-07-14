package com.outsider.masterofprediction.controller.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mypage/")
public class InquiryController {



    @GetMapping("inquiry/register")
    public ModelAndView getInquiryRegister(ModelAndView mv) {
        // Method logic here
        mv.setViewName("/layout/my-page/index");
        mv.addObject("title", "Home Page");
        mv.addObject("view", "content/my-page/inquiry-register");
        return mv;
    }

    @GetMapping("inquiry/detail/{inquiryNo}")
    public ModelAndView getInquiryDetail(@PathVariable int inquiryNo, @RequestParam int replyStatus ,ModelAndView mv) {
        // Method logic here
        mv.setViewName("/layout/my-page/index");
        mv.addObject("title", "Home Page");
        mv.addObject("view", "content/my-page/inquiry-detail");
        mv.addObject("replyStatus", replyStatus);
        return mv;
    }

    @PostMapping("inquiry/register")
    public String postInquiryRegister(
            RedirectAttributes redirectAttributes) {
        // Method logic here
        return "redirect:/mypage/inquirys/1";
    }
}
