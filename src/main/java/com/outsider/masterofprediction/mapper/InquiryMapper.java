package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.CustomTblInquiryDTO;
import com.outsider.masterofprediction.dto.InquiryDetailDTO;
import com.outsider.masterofprediction.dto.TblInquiryDTO;
import com.outsider.masterofprediction.dto.constatnt.InquiryReplyStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.outsider.masterofprediction.dto.UserPaginationDTO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface InquiryMapper {
    @Select("SELECT *" +
            "FROM " +
            "    tbl_inquiry  " +
            " LIMIT #{first}, #{count};")
    List<TblInquiryDTO> getInquiryLimit(int first, int count);

    @Select("SELECT *" +
            "FROM " +
            "    tbl_inquiry")
    List<CustomTblInquiryDTO> getInquiryAll();
    // 문의 등록
    void insertInquiry(TblInquiryDTO inquiry);

    // 문의 내역 조회
    List<TblInquiryDTO> getInquiriesByUserId(UserPaginationDTO userPaginationDTO);

    @Update("UPDATE tbl_inquiry set inquiry_reply_status=#{replyStatus} where inquiry_no=#{inquiryNo}")
    void updateReplyStatus(int inquiryNo, int replyStatus);
    // 최근 문의 내역 조회
    String getLatestInquiryByUserId(Long userId);

    @Select("SELECT COUNT(*) as count FROM tbl_inquiry")
    int totalCount();
    // 전체 문의 수 조회 (페이지네이션용)
    int getTotalInquiriesCountByUserId(@Param("userId") Long userId);

    @Select("SELECT *" +
            "FROM " +
            "    tbl_inquiry  " +
            "LIMIT #{first}, #{count};")
    List<CustomTblInquiryDTO> getTBLInquiryLimit(int first, int count);
    // 상세 문의 조회
    InquiryDetailDTO getInquiryDetailByUserId(TblInquiryDTO tblInquiryDTO);

   @Select("SELECT * FROM tbl_inquiry where inquiry_no = #{inquiryNo}")
    CustomTblInquiryDTO getTBLInquiryById(Long inquiryNo);
    @Select("SELECT inquiry_reply_status FROM tbl_inquiry where inquiry_no = #{inquiryNo}")
    int getInquiryReplyStatus(TblInquiryDTO inquiryDTO);
   // 결과 저장
    @Update("UPDATE tbl_inquiry SET inquiry_reply_status= #{inquiryReplyStatus.value} where inquiry_no=#{inquiryNo}")
    void updateState(InquiryReplyStatus inquiryReplyStatus, Long inquiryNo);
}
