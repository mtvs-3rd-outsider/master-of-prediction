package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.TblCategoryDTO;
import com.outsider.masterofprediction.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public List<TblCategoryDTO> findAll() {
        return categoryMapper.findAll();
    }

    public List<TblCategoryDTO> findCategoryLimit(int first, int count) {
        return categoryMapper.categoryListLimit(first, count);
    }

    public void create(String categoryName) {
        categoryMapper.insertCategory(categoryName);
    }

    public void deleteByIds(List<Long> ids) {
        categoryMapper.deleteByIds(ids);
    }

    public void updateById(TblCategoryDTO tblCategoryDTO) {
        categoryMapper.updateCategory(tblCategoryDTO);
    }

    public int totalCount() {
        return categoryMapper.totalCount();
    }

    public TblCategoryDTO findByCategoryNo(long subjectCategoryNo) {
        return categoryMapper.findByCategoryNo(subjectCategoryNo);
    }
}
