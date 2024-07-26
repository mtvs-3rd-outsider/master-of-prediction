package com.outsider.masterofprediction.controller;

import com.mysql.cj.util.StringUtils;
import com.outsider.masterofprediction.dto.CategoryPageSubjectDTO;
import com.outsider.masterofprediction.dto.SearchPageSubjectDTO;
import com.outsider.masterofprediction.mapper.SearchPageMapper;
import com.outsider.masterofprediction.service.CategoryService;
import com.outsider.masterofprediction.service.SearchPageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Controller
@RequestMapping("/search")
public class SearchPageController {

    private SearchPageService searchPageService;
    private CategoryService categoryService;

    @Autowired
    public SearchPageController(SearchPageService searchPageService, CategoryService categoryService) {
        this.searchPageService = searchPageService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ModelAndView getSearchPage(ModelAndView mv,
                                      @RequestParam(value="searchWord", required=false, defaultValue = "") String searchWord) {

        List<String> wordList = new ArrayList<String>();

        String[] splitStr = searchWord.split(" ");

        for(int i = 0; i < splitStr.length; i++) {
            if(splitStr[i].length() > 0) {
                wordList.add(splitStr[i]);
            }
        }

        System.out.println(wordList);

        List<SearchPageSubjectDTO> searchPageSubjectDTOs =  searchPageService.findSearchPageSubjectList(wordList);

        mv.setViewName("layout/index");
        mv.addObject("subjects", searchPageSubjectDTOs);
        mv.addObject("categories", categoryService.findAll());
        mv.addObject("title", "Master Of Prediction");
        mv.addObject("view", "content/search");

        return mv;
    }
}
