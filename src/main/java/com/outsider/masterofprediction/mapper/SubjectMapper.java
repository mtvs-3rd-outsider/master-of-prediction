package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.TblSubjectDTO;
import com.outsider.masterofprediction.dto.response.BettingAndAttachmentDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SubjectMapper {

    @Select("SELECT *" +
            "FROM " +
            "    TBL_SUBJECT  " +
            "LEFT JOIN " +
            "    TBL_ATTACHMENT  ON tbl_subject.subject_no = tbl_attachment.subject_no;")
    List<BettingAndAttachmentDTO> getSubjectsJoinAttachments();

    @Select("SELECT *" +
            "FROM " +
            "    TBL_SUBJECT  " +
            "LEFT JOIN " +
            "    TBL_ATTACHMENT  ON tbl_subject.subject_no = tbl_attachment.subject_no LIMIT #{first}, #{count};")
    List<BettingAndAttachmentDTO> getSubjectsJoinAttachmentsLimit(int first, int count);

    @Select("SELECT *" +
            "FROM " +
            "    TBL_SUBJECT  " +
            "LEFT JOIN " +
            "    TBL_ATTACHMENT  ON tbl_subject.subject_no = tbl_attachment.subject_no " +
            "where tbl_subject.subject_no = #{subjectNo};")
    BettingAndAttachmentDTO getSubjectJoinAttachmentBySubjectNo(Long subjectNo);

    @Select("SELECT * FROM tbl_subject WHERE subject_no = #{subjectNo}")
    TblSubjectDTO getSubjectById(long subjectNo);

    @Select("SELECT * FROM tbl_subject")
    List<TblSubjectDTO> getAllSubjects();


    @Select("SELECT COUNT(*) as count FROM tbl_subject")
    int totalCount();

    @Select("SELECT * FROM tbl_subject WHERE subject_no=#{subjectNo}")
    TblSubjectDTO getSubjectBySubjectNo(long subjectNo);


    @Insert("INSERT INTO tbl_subject (" +
            "subject_title, " +
            "subject_status, " +
            "subject_category_no, " +
            "subject_register_user_no," +
            "subject_settlement_timestamp" +
            ") " +
            "VALUES (" +
            "#{subjectTitle}, " +
            "#{subjectStatus}, " +
            "#{subjectCategoryNo}, " +
            "#{subjectRegisterUserNo}," +
            "#{subjectSettlementTimestamp})")
    @Options(useGeneratedKeys = true, keyProperty = "subjectNo")
    void insertSubject(TblSubjectDTO subject);

    @Update("UPDATE tbl_subject SET " +
            "subject_title = #{subjectTitle}, " +
            "subject_total_yes_point = #{subjectTotalYesPoint}, " +
            "subject_status = #{subjectStatus}, " +
            "subject_register_timestamp = #{subjectRegisterTimestamp}, " +
            "subject_finish_timestamp = #{subjectFinishTimestamp}, " +
            "subject_category_no = #{subjectCategoryNo}, " +
            "subject_register_user_no = #{subjectRegisterUserNo}, " +
            "subject_finish_result = #{subjectFinishResult}, " +
            "subject_total_no_point = #{subjectTotalNoPoint} " +
            "WHERE subject_no = #{subjectNo}")
    void updateSubject(TblSubjectDTO subject);

    @Update("UPDATE tbl_subject SET " +
            "subject_status = #{subjectStatus}, " +
            "subject_finish_timestamp = #{subjectFinishTimestamp}, " +
            "subject_finish_result = #{subjectFinishResult} " +
            "WHERE subject_no = #{subjectNo}")
    void finishSubject(TblSubjectDTO subject);

    @Update("UPDATE tbl_subject SET " +
            "subject_title = #{subjectTitle}, " +
            "subject_settlement_timestamp = #{subjectSettlementTimestamp}, " +
            "subject_category_no = #{subjectCategoryNo} " +
            "WHERE subject_no = #{subjectNo}")
    void updateSubjectTitleAndCategoryAndSettlementTimestamp(TblSubjectDTO subject);

    @Update("UPDATE tbl_subject SET " +
            "subject_finish_result = #{subjectFinishResult}, " +
            "subject_finish_timestamp = #{subjectFinishTimestamp}, " +
            "subject_status = #{subjectFinishTimestamp} " +
            "WHERE subject_no = #{subjectNo}")
    void updateSubjectResult(TblSubjectDTO subject);



    @Update("UPDATE tbl_subject SET " +
            "subject_total_yes_point = #{subjectTotalYesPoint}, " +
            "subject_total_no_point = #{subjectTotalNoPoint}, " +
            "WHERE subject_no = #{num}")
    void updateSubjectPoint(double num);



    @Delete("DELETE FROM tbl_subject WHERE subject_no = #{subjectNo}")
    void deleteSubject(long subjectNo);


}
