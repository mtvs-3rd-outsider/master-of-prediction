package com.outsider.masterofprediction.controller.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mypage")
public class MyPageController {



    @GetMapping()
    public ModelAndView getMyPage(ModelAndView mv) {
        Long id = 1L; // Dummy user ID

        mv.setViewName("/layout/my-page/index");
        mv.addObject("title", "Home Page");
        mv.addObject("view", "content/my-page/my-page");
        mv.addObject("name", "Dummy User"); // Dummy username

        return mv;
    }



    @GetMapping("/withdrawal")
    public ModelAndView getWithdrawal(ModelAndView mv) {
        mv.setViewName("/layout/my-page/index");
        mv.addObject("title", "Home Page");
        mv.addObject("view", "content/my-page/withdrawal");
        return mv;
    }
    @PostMapping("/withdrawal")
    public ModelAndView postWithdrawal(ModelAndView mv) {
        mv.setViewName("/");

        return mv;
    }

    @GetMapping("/change-personal-information")
    public ModelAndView getChangePersonalInformation(ModelAndView mv) {
        Long userId = 1L; // Dummy user ID
        mv.setViewName("/layout/my-page/index");
        mv.addObject("title", "Home Page");
        mv.addObject("view", "content/my-page/change-personal-information");
        mv.addObject("name", "Dummy User"); // Dummy username
        mv.addObject("password", "dummyPassword"); // Dummy password
        return mv;
    }

    @PostMapping("/change-personal-information")
    public String submitChangePersonalInformationForm(@RequestParam("profileImage") MultipartFile profileImage, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("flashMessage1", "변경 사항이 성공적으로 저장되었습니다.");
        return "redirect:/mypage";
    }


}
