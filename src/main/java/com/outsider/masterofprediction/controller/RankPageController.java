package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.*;
import com.outsider.masterofprediction.service.RankFindService;
import com.outsider.masterofprediction.utils.ConvertImageUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/rankpage")
public class RankPageController {

    private final RankFindService rankFindService;

    public RankPageController(RankFindService rankFindService){
        this.rankFindService = rankFindService;
    }

    @GetMapping
    public ModelAndView getRankPage(ModelAndView mv){

        mv.setViewName("layout/index");
        mv.addObject("title", "User Ranking");
        mv.addObject("view", "content/rank-page");

        List<UserAttachmentDTO> userRank = rankFindService.findAllRank();

        System.out.println(userRank);
        ConvertImageUrl.convert(userRank);

        mv.addObject("userRank", userRank);
        return mv;
    }
}
