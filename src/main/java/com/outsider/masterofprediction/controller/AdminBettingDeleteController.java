package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.service.BettingDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminBettingDeleteController {

    private BettingDeleteService bettingDeleteService;

    public AdminBettingDeleteController(){
    }

    @Autowired
    public AdminBettingDeleteController(BettingDeleteService bettingDeleteService) {
        this.bettingDeleteService = bettingDeleteService;
    }

    @PostMapping("/admin-page/betting/delete")
    public String deleteBetting(@RequestParam List<Long> ids) {
        bettingDeleteService.deleteList(ids);
        return "redirect:/admin-page/betting";
    }
}
