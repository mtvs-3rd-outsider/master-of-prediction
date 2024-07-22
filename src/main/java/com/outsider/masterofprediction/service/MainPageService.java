package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.response.MainPageSubjectDTO;
import com.outsider.masterofprediction.mapper.MainPageSubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainPageService {

    private final MainPageSubjectMapper mainPageSubjectMapper;

    @Autowired
    public MainPageService(MainPageSubjectMapper mainPageSubjectMapper) {
        this.mainPageSubjectMapper = mainPageSubjectMapper;
    }

    public List<MainPageSubjectDTO> findMainPageSubjectList(){
        return mainPageSubjectMapper.findMainPageSubjectList();
    }

}
