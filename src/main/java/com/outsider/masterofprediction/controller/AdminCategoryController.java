package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.TblCategoryDTO;
import com.outsider.masterofprediction.dto.constatnt.AdminPaginationConstant;
import com.outsider.masterofprediction.service.CategoryService;
import com.outsider.masterofprediction.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminCategoryController {

    private final CategoryService categoryService;

    @Autowired
    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/admin-page/category")
public String showCategoryPage(@RequestParam(defaultValue = "1")int page, Model model) {
        if (page < 1) page = 1;

        int totalCount = categoryService.totalCount();
        List<TblCategoryDTO> tblCategoryDTO =
                categoryService.findCategoryLimit((page - 1) * 10, AdminPaginationConstant.itemCount);
        PaginationUtils.modelSetPaginationReturnPageNumber(model, page, totalCount);
        model.addAttribute("category", tblCategoryDTO);
        return "content/admin-page/category/index";
    }

    @PostMapping("/admin-page/category/create")
    public String create(String categoryName) {
        categoryService.create(categoryName);
        return "redirect:/admin-page/category";
    }

    @PostMapping("/admin-page/category/update")
    public String update(@ModelAttribute TblCategoryDTO tblCategoryDTO) {
        categoryService.updateById(tblCategoryDTO);
        return "redirect:/admin-page/category";
    }

    @PostMapping("/admin-page/category/delete")
    public String delete(@RequestParam("ids") List<Long> ids) {
        categoryService.deleteByIds(ids);
        return "redirect:/admin-page/category";
    }
}
