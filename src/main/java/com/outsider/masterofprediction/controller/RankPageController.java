package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.*;
import com.outsider.masterofprediction.service.CategoryService;
import com.outsider.masterofprediction.service.RankFindService;
import com.outsider.masterofprediction.service.UserManagementService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/rankpage")
public class RankPageController {

    private final RankFindService rankFindService;
    private final CategoryService categoryService;
    private final UserManagementService userManagementService;

    public RankPageController(RankFindService rankFindService, CategoryService categoryService, UserManagementService userManagementService){
        this.rankFindService = rankFindService;
        this.categoryService = categoryService;
        this.userManagementService = userManagementService;
    }

    @GetMapping
    public ModelAndView getRankPage(ModelAndView mv){

        // mv.addObject();
        mv.setViewName("/layout/rank-page/index");
        mv.addObject("title", "User Ranking");
        mv.addObject("view", "content/rank-page/rank-page");
        mv.addObject("categories", categoryService.findAll());

//        List<TblAttachmentDTO> userList = rankFindService.findRank();
//        mv.addObject("userList", userList);

        List<UserAttachmentDTO> userRank = rankFindService.findAllRank();
        
        mv.addObject("userRank", userRank);
        System.out.println(userRank);

        return mv;
    }
}
