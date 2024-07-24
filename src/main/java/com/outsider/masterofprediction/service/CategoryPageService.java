package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.CategoryPageSubjectDTO;
import com.outsider.masterofprediction.dto.response.MainPageSubjectDTO;
import com.outsider.masterofprediction.mapper.CategoryPageSubjectMapper;
import com.outsider.masterofprediction.mapper.MainPageSubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryPageService {

    private CategoryPageSubjectMapper categoryPageSubjectMapper;

    @Autowired
    public CategoryPageService(CategoryPageSubjectMapper categoryPageSubjectMapper) {
        this.categoryPageSubjectMapper = categoryPageSubjectMapper;
    }

    public List<CategoryPageSubjectDTO> findCategoryPageSubjectList(int categoryNo) {
        return categoryPageSubjectMapper.findCategoryPageSubjectList(categoryNo);
    }

    public List<CategoryPageSubjectDTO> findAllCategoryPageList() {
        return categoryPageSubjectMapper.findAllCategoryPageList();
    }

    public List<CategoryPageSubjectDTO> findRecentCategoryPageList() {
        return categoryPageSubjectMapper.findRecentCategoryPageList();
    }
}
