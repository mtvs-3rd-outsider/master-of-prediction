package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.CustomTblInquiryDTO;
import com.outsider.masterofprediction.dto.TblInquiryDTO;
import com.outsider.masterofprediction.dto.constatnt.InquiryReplyStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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


    @Update("UPDATE tbl_inquiry set inquiry_reply_status=#{replyStatus} where inquiry_no=#{inquiryNo}")
    void updateReplyStatus(int inquiryNo, int replyStatus);

    @Select("SELECT COUNT(*) as count FROM tbl_inquiry")
    int totalCount();

    @Select("SELECT *" +
            "FROM " +
            "    tbl_inquiry  " +
            "LIMIT #{first}, #{count};")
    List<CustomTblInquiryDTO> getTBLInquiryLimit(int first, int count);

   @Select("SELECT * FROM tbl_inquiry where inquiry_no = #{inquiryNo}")
    CustomTblInquiryDTO getTBLInquiryById(Long inquiryNo);

   // 결과 저장
    @Update("UPDATE tbl_inquiry SET inquiry_reply_status= #{inquiryReplyStatus.value} where inquiry_no=#{inquiryNo}")
    void updateState(InquiryReplyStatus inquiryReplyStatus, Long inquiryNo);
}
