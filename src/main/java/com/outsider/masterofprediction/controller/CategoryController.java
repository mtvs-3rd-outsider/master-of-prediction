package com.outsider.masterofprediction.controller;


import com.outsider.masterofprediction.dto.CategoryPageSubjectDTO;
import com.outsider.masterofprediction.service.CategoryPageService;
import com.outsider.masterofprediction.service.CategoryService;
import com.outsider.masterofprediction.utils.AttachmentFileAddressable;
import com.outsider.masterofprediction.utils.ConvertImageUrl;
import com.outsider.masterofprediction.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryPageService categoryPageService;

    @Autowired
    public CategoryController(CategoryPageService categoryPageService) {
        this.categoryPageService = categoryPageService;
    }

    private void common(ModelAndView mv){
        mv.setViewName("layout/index");
        mv.addObject("title", "Master Of Prediction");
        mv.addObject("view", "content/category");
    }


    @GetMapping("/{categoryNo}")
    public ModelAndView getCategoryNoPage(ModelAndView mv, @PathVariable("categoryNo") int categoryNo){

        List<CategoryPageSubjectDTO> categoryPageSubjectDTOs =  categoryPageService.findCategoryPageSubjectList(categoryNo);

        common(mv);
        ConvertImageUrl.convert(categoryPageSubjectDTOs);
        mv.addObject("subjects", categoryPageSubjectDTOs);
        return mv;
    }

    @GetMapping("/all")
    public ModelAndView getAllCategoryPage(ModelAndView mv){

        List<CategoryPageSubjectDTO> categoryPageSubjectDTOs =  categoryPageService.findAllCategoryPageList();

        common(mv);
        ConvertImageUrl.convert(categoryPageSubjectDTOs);
        mv.addObject("subjects", categoryPageSubjectDTOs);
        return mv;
    }

    @GetMapping("/recent")
    public ModelAndView getRecentCategoryPage(ModelAndView mv){

        List<CategoryPageSubjectDTO> categoryPageSubjectDTOs =  categoryPageService.findRecentCategoryPageList();

        common(mv);
        ConvertImageUrl.convert(categoryPageSubjectDTOs);
        mv.addObject("subjects", categoryPageSubjectDTOs);
        return mv;
    }

}
