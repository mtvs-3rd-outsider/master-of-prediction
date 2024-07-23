package com.outsider.masterofprediction.controller;


import com.outsider.masterofprediction.dto.CategoryPageSubjectDTO;
import com.outsider.masterofprediction.dto.response.MainPageSubjectDTO;
import com.outsider.masterofprediction.service.CategoryPageService;
import com.outsider.masterofprediction.service.CategoryService;
import com.outsider.masterofprediction.service.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryPageService categoryPageService;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryPageService categoryPageService, CategoryService categoryService) {
        this.categoryPageService = categoryPageService;
        this.categoryService = categoryService;
    }

    @GetMapping("/{categoryNo}")
    public ModelAndView getCategoryNoPage(ModelAndView mv, @PathVariable("categoryNo") int categoryNo){

        List<CategoryPageSubjectDTO> categoryPageSubjectDTOs =  categoryPageService.findCategoryPageSubjectList(categoryNo);

        mv.setViewName("/layout/category/index");
        mv.addObject("subjects", categoryPageSubjectDTOs);
        mv.addObject("categories", categoryService.findAll());
        mv.addObject("title", "Master Of Prediction");
        mv.addObject("view", "content/category/category");

        return mv;
    }

    @GetMapping("/all")
    public ModelAndView getAllCategoryPage(ModelAndView mv){

        List<CategoryPageSubjectDTO> categoryPageSubjectDTOs =  categoryPageService.findAllCategoryPageList();

        mv.setViewName("/layout/category/index");
        mv.addObject("subjects", categoryPageSubjectDTOs);
        mv.addObject("categories", categoryService.findAll());
        mv.addObject("title", "Master Of Prediction");
        mv.addObject("view", "content/category/category");

        return mv;
    }

    @GetMapping("/recent")
    public ModelAndView getRecentCategoryPage(ModelAndView mv){

        List<CategoryPageSubjectDTO> categoryPageSubjectDTOs =  categoryPageService.findRecentCategoryPageList();

        mv.setViewName("/layout/category/index");
        mv.addObject("subjects", categoryPageSubjectDTOs);
        mv.addObject("categories", categoryService.findAll());
        mv.addObject("title", "Master Of Prediction");
        mv.addObject("view", "content/category/category");

        return mv;
    }

}
