package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.*;
import com.outsider.masterofprediction.service.BettingOrderService;
import com.outsider.masterofprediction.service.SubjectService;
import com.outsider.masterofprediction.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BettingController {
    private final BettingOrderService bettingOrderService;
    private final SubjectService subjectService;
    private final UserManagementService userManagementService;

    @Autowired
    public BettingController(BettingOrderService bettingOrderService, SubjectService subjectService, UserManagementService userManagementService) {
        this.bettingOrderService = bettingOrderService;
        this.subjectService = subjectService;
        this.userManagementService = userManagementService;
    }


    @GetMapping("/betting")
    public String getBettingPage(Model model) {
        long subjectNo = 1l;
        TblSubjectDTO subject= subjectService.getSubjectBySubjectNo(subjectNo);
        String userAuthority = userManagementService.getAuthorityBySubjectUserNo(91);

        String returnYRate =  String.valueOf((int)((float)subject.getSubjectTotalNoPoint()/subject.getSubjectTotalYesPoint()*100))+"%";
        String returnNRate = String.valueOf((int)((float)subject.getSubjectTotalYesPoint()/subject.getSubjectTotalNoPoint()*100))+"%";

        model.addAttribute("returnYRate", returnYRate);
        model.addAttribute("returnNRate", returnNRate);
        model.addAttribute("subject", subject);
        model.addAttribute("userAuthority", userAuthority);

        return "/content/betting-page/betting-page";
    }

    /**
     * 활동내역조회 컨트롤러
     * @return
     */
    @GetMapping(value = "/active",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<ActiveDTO> getActives() {
        Long subjectNo =1l;
        return bettingOrderService.getBettingOrdersBySubjectNo(subjectNo);
    }

    @GetMapping(value = "/ranking",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<RankingDTO> getRankings() {
        Long subjectNo =1l;
        return bettingOrderService.getRankingBySubjectNo(subjectNo);
    }


//    @PostMapping(value ="/comment",produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    public
}
