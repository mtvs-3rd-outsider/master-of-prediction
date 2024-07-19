package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.BettingAddDTO;
import com.outsider.masterofprediction.dto.TblCategoryDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BettingAddMapper {

    @Select("SELECT * FROM tbl_category")
    List<TblCategoryDTO> findAll();

    @Results(id = "bettingAddResultMap", value = {
            @Result(property = "subjectNo", column = "subject_no"),
            @Result(property = "subjectTitle", column = "subject_title"),
            @Result(property = "subjectTotalYesPoint", column = "subject_total_yes_point"),
            @Result(property = "subjectTotalNoPoint", column = "subject_total_no_point"),
            @Result(property = "subjectStatus", column = "subject_status"),
            @Result(property = "subjectRegisterTimestamp", column = "subject_register_timestamp"),
            @Result(property = "subjectFinishTimestamp", column = "subject_finish_timestamp"),
            @Result(property = "subjectSettlementTimestamp", column = "subject_settle_timestamp"),
            @Result(property = "subjectCategoryNo", column = "subject_category_no"),
            @Result(property = "subjectRegisterUserNo", column = "subject_register_user_no"),
            @Result(property = "subject_finish_result", column = "subject_finish_result"),
    })

    @Insert("INSERT INTO tbl_subject (subject_title, subject_category_no, subject_finish_timestamp, subject_status, subject_register_user_no) " +
            "values (#{subjectTitle}, #{subjectCategoryNo}, #{subjectFinishTimestamp}, '진행', #{subjectRegisterUserNo} )")
    @ResultMap("bettingAddResultMap")
    void create(BettingAddDTO bettingAddDTO);

}
