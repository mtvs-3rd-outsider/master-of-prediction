package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.UserPaginationDTO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface InquiryMapper {

    // 문의 등록
    void insertInquiry(TblInquiryDTO inquiry);

    // 문의 내역 조회
    List<TblInquiryDTO> getInquiriesByUserId(UserPaginationDTO userPaginationDTO);

    // 최근 문의 내역 조회
    String getLatestInquiryByUserId(@Param("userId") Long userId);

    // 전체 문의 수 조회 (페이지네이션용)
    int getTotalInquiriesCountByUserId(@Param("userId") Long userId);

    // 상세 문의 조회
    InquiryDetailDTO getInquiryDetailByUserId(@Param("userId") Long userId, @Param("inquiryNo") int inquiryNo, @Param("replyStatus") int replyStatus);

}
