package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserNoticeMapper {

    @Results(id = "noticeResultMap", value = {
            @Result(property = "id", column = "notice_no"),
            @Result(property = "title", column = "notice_title"),
            @Result(property = "content", column = "notice_content"),
            @Result(property = "timestamp", column = "notice_timestamp"),
            @Result(property = "userId", column = "notice_user_no")
    })

    @Select("SELECT * FROM TBL_NOTICE")
    List<Notice> getAllList();

    @Select("SELECT COUNT(*) as count FROM tbl_notice")
    int totalCount();

    @Select("SELECT notice_no, notice_title, notice_timestamp from tbl_notice ORDER BY notice_timestamp DESC limit #{first}, #{count}")
    @ResultMap("noticeResultMap")
    List<Notice> noticeListLimit(int first, int count);

    @Select("SELECT notice_no, notice_title, notice_content, notice_timestamp from tbl_notice where notice_no = #{notificationId}")
    @ResultMap("noticeResultMap")
    Notice getNoticeById(Long notificationId);
}
