package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.ActiveDTO;
import com.outsider.masterofprediction.dto.BettingOrderDTO;
import com.outsider.masterofprediction.dto.RankingDTO;
import com.outsider.masterofprediction.dto.TblBettingOrderDTO;
import com.outsider.masterofprediction.service.BettingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BettingController {
    private final BettingOrderService bettingOrderService;

    @Autowired
    public BettingController(BettingOrderService bettingOrderService) {
        this.bettingOrderService = bettingOrderService;
    }


    @GetMapping("/betting")
    public String getBettingPage() {
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
}
