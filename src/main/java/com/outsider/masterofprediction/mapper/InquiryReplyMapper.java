package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.TblInquiryReplyDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InquiryReplyMapper {
    @Insert("insert into tbl_inquiry_reply(" +
            "answer_title, " +
            "answer_content, " +
            "answer_user_no, " +
            "answer_inquiry_no" +
            ") values (" +
            "#{answerTitle}, " +
            "#{answerContent}, " +
            "#{answerUserNo}, " +
            "#{answerInquiryNo}" +
            ")")
    void insertInquiry(TblInquiryReplyDTO reply);
}
