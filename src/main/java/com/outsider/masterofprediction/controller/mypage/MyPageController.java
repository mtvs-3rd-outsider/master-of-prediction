package com.outsider.masterofprediction.controller.mypage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/mypage")
public class MyPageController {



    @GetMapping()
    public ModelAndView getMyPage(ModelAndView mv) {
        Long id = 1L; // Dummy user ID

        mv.setViewName("layout/my-page/layout");
        mv.addObject("title", "Home Page");
        mv.addObject("view", "content/my-page/my-page");
        mv.addObject("name", "Dummy User"); // Dummy username

        return mv;
    }

    @GetMapping("/purchase-history/{page}")
    public ModelAndView getPurchaseHistory(@PathVariable int page, ModelAndView mv) {
        Long userId = 1L; // Dummy user ID

        mv.setViewName("layout/my-page/layout");
        mv.addObject("title", "Home Page");
        mv.addObject("view", "content/my-page/purchase-history");
        int itemsPerPage = 4;
        return mv;
    }

    @GetMapping("/withdrawal")
    public ModelAndView getWithdrawal(ModelAndView mv) {
        mv.setViewName("layout/my-page/layout");
        mv.addObject("title", "Home Page");
        mv.addObject("view", "content/my-page/withdrawal");
        return mv;
    }

    @GetMapping("/change-personal-information")
    public ModelAndView getChangePersonalInformation(ModelAndView mv) {
        Long userId = 1L; // Dummy user ID
        mv.setViewName("layout/my-page/layout");
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

    @GetMapping("/comments/{page}")
    public ModelAndView comment(@PathVariable int page, ModelAndView mv) {
        Long userId = 1L; // Dummy user ID
        mv.setViewName("layout/my-page/layout");
        mv.addObject("title", "Home Page");
        mv.addObject("view", "content/my-page/comments");
        return mv;
    }
}
