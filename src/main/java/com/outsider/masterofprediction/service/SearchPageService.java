package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.SearchPageSubjectDTO;
import com.outsider.masterofprediction.mapper.SearchPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchPageService {

    private SearchPageMapper searchPageMapper;

    @Autowired
    public SearchPageService(SearchPageMapper searchPageMapper) {
        this.searchPageMapper = searchPageMapper;
    }

    public List<SearchPageSubjectDTO> findSearchPageSubjectList(String searchWord){
        return searchPageMapper.findSearchPageSubjectList(searchWord);
    }
}
