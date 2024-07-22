package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.TblAttachmentDTO;
import com.outsider.masterofprediction.dto.TblUserDTO;
import com.outsider.masterofprediction.dto.User;
import com.outsider.masterofprediction.dto.UserAttachmentDTO;
import com.outsider.masterofprediction.service.RankFindService;
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

    public RankPageController(RankFindService rankFindService){
        this.rankFindService = rankFindService;
    }

    @GetMapping
    public ModelAndView getRankPage(ModelAndView mv){


        // mv.addObject();
        mv.setViewName("/layout/rank-page/index");
        mv.addObject("title", "User Ranking");
        mv.addObject("view", "content/rank-page/rank-page");

//        List<TblAttachmentDTO> userList = rankFindService.findRank();
//        mv.addObject("userList", userList);

        List<UserAttachmentDTO> userRank = rankFindService.findAllRank();
        mv.addObject("userRank", userRank);

        System.out.println(userRank);

        return mv;
    }
}
