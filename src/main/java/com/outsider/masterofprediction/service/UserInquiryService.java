package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.InquiryDetailDTO;
import com.outsider.masterofprediction.dto.TblInquiryDTO;
import com.outsider.masterofprediction.dto.UserPaginationDTO;
import com.outsider.masterofprediction.mapper.InquiryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInquiryService {

    private InquiryMapper inquiryMapper;

    public UserInquiryService(InquiryMapper inquiryMapper) {
        this.inquiryMapper = inquiryMapper;
    }

    public void insertInquiry(TblInquiryDTO inquiry) {
        inquiryMapper.insertInquiry(inquiry);
    }

    public List<TblInquiryDTO> getInquiriesByUserId(UserPaginationDTO userPaginationDTO) {
        return inquiryMapper.getInquiriesByUserId(userPaginationDTO);
    }
    public int getTotalInquiries(Long userId) {
        return inquiryMapper.getTotalInquiriesCountByUserId(userId);
    }

    public InquiryDetailDTO getInquiryDetail(TblInquiryDTO tblInquiryDTO) {
        return inquiryMapper.getInquiryDetailByUserId(tblInquiryDTO);
    }
}
