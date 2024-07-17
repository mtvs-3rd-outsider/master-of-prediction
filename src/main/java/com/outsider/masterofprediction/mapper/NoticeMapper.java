package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.Notice;
import com.outsider.masterofprediction.provider.DeleteByIdsProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoticeMapper {

    @Results(id = "noticeMap", value = {
            @Result(property = "id", column = "notice_no"),
            @Result(property = "title", column = "notice_title"),
            @Result(property = "content", column = "notice_content"),
            @Result(property = "timestamp", column = "notice_timestamp"),
            @Result(property = "userId", column = "notice_user_no")
    })
    @Select("select  * from tbl_notice")
    List<Notice> selectAll();

    @Select("select notice_no, notice_title, notice_timestamp from tbl_notice")
    @ResultMap("noticeMap")
    List<Notice> noticeList();

    @Select("select notice_no, notice_title, notice_timestamp from tbl_notice limit #{first}, #{count}")
    @ResultMap("noticeMap")
    List<Notice> noticeListLimit(int first, int count);

    @Select("select * from tbl_notice where notice_no = #{id};")
    @ResultMap(("noticeMap"))
    Notice selectById(Long id);

    @Update("update tbl_notice" +
            " set notice_title = #{title}," +
            "notice_content = #{content}," +
            "notice_timestamp = #{timestamp} " +
            "where notice_no = #{id}")
    int updateDetail(Notice notice);


    @DeleteProvider(type = DeleteByIdsProvider.class, method = "deleteByIdsProvider")
    void deleteAllByIds(List<Long> ids);

    @Insert("insert into tbl_notice(" +
            "notice_title," +
            "notice_content," +
            "notice_user_no " +
            ") VALUES  (#{title}, #{content}, #{userId})")
    void create(Notice notice);


    @Select("SELECT COUNT(*) as count FROM tbl_notice")
    int totalCount();
}
