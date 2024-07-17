package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.TblTierDTO;
import com.outsider.masterofprediction.dto.constatnt.AdminPaginationConstant;
import com.outsider.masterofprediction.service.TierService;
import com.outsider.masterofprediction.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin-page/tier")
public class TierController {

    private final TierService tierService;

    @Autowired
    public TierController(TierService tierService) {
        this.tierService = tierService;
    }


    @GetMapping
    public String showCategoryPage(@RequestParam(defaultValue = "1")int page, Model model) {
        if (page < 1) page = 1;

        int totalCount = tierService.totalCount();
        List<TblTierDTO> tblTierDTO =
                tierService.findCategoryLimit((page - 1) * 10, AdminPaginationConstant.itemCount);
        PaginationUtils.modelSetPaginationReturnPageNumber(model, page, totalCount);
        model.addAttribute("tier", tblTierDTO);
        return "content/admin-page/tier/index";
    }



}
