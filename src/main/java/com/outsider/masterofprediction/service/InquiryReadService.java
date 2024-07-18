package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.CustomTblInquiryDTO;
import com.outsider.masterofprediction.mapper.InquiryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryReadService {

    private final InquiryMapper inquiryMapper;

    @Autowired
    public InquiryReadService(InquiryMapper inquiryMapper) {
        this.inquiryMapper = inquiryMapper;
    }

    public List<CustomTblInquiryDTO> getInquiryAll() {
        return inquiryMapper.getInquiryAll();
    }

    public int getTotalCount(){
        return inquiryMapper.totalCount();
    }

    public List<CustomTblInquiryDTO> searchNoticeLimit(int page, int itemCount) {
        return inquiryMapper.getTBLInquiryLimit(page, itemCount);
    }

    public CustomTblInquiryDTO getTBLInquiryById(Long inquiryNo) {
        return inquiryMapper.getTBLInquiryById(inquiryNo);
    }

}
