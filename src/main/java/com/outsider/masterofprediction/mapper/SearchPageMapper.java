package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.CategoryPageSubjectDTO;
import com.outsider.masterofprediction.dto.SearchPageSubjectDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SearchPageMapper {

    List<SearchPageSubjectDTO> findSearchPageSubjectList(List<String> wordList);

}
