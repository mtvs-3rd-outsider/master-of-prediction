package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.TblAttachmentDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttachmentMapper {

    @Select("SELECT * FROM tbl_attachment")
    public List<TblAttachmentDTO> getAllAttachments();

    @Select("SELECT * FROM tbl_attachment WHERE attachment_regist_user_no=#{id}")
    public TblAttachmentDTO getAttachmentsByAttachmentRegistUserNo(Long id);

    @Select("SELECT * FROM tbl_attachment WHERE answer_no=#{id}")
    public TblAttachmentDTO getAttachmentsByAnswerNo(Long id);

    @Select("SELECT * FROM tbl_attachment WHERE inquiry_no=#{id}")
    public TblAttachmentDTO getAttachmentsByInquiryNo(Long id);

    @Select("SELECT * FROM tbl_attachment WHERE subject_no=#{id}")
    public TblAttachmentDTO getAttachmentsBySubjectNo(Long id);

    @Select("SELECT * FROM tbl_attachment WHERE notice_no=#{id}")
    public TblAttachmentDTO getAttachmentsByNoticeNo(Long id);

    @Select("SELECT * FROM tbl_attachment WHERE attachment_user_no=#{id}")
    public TblAttachmentDTO getAttachmentsByUserNo(Long id);

    @Select("SELECT * FROM tbl_attachment WHERE tier_no=#{id}")
    public TblAttachmentDTO getAttachmentsByTierNo(Long id);

    @Insert("INSERT INTO tbl_attachment (attachment_file_address, attachment_regist_user_no, subject_no) " +
            "values (#{attachmentFileAddress}, #{attachmentRegistUserNo}, #{subjectNo})")
    public void setAttachmentsBySubjectNo(TblAttachmentDTO tblAttachmentDTO);

    @Insert("INSERT INTO tbl_attachment (attachment_file_address, attachment_regist_user_no, inquiry_no) " +
            "values (#{attachmentFileAddress}, #{attachmentRegistUserNo}, #{subjectNo})")
    public void setAttachmentsByInquiryNo(TblAttachmentDTO tblAttachmentDTO);


    @Insert("INSERT INTO tbl_attachment (attachment_file_address, attachment_regist_user_no, attachment_user_no) " +
            "values (#{attachmentFileAddress}, #{attachmentRegistUserNo}, #{attachmentUserNo})")
    public void setAttachmentsByAttachmentUserNo(TblAttachmentDTO tblAttachmentDTO);

    @Insert("INSERT INTO tbl_attachment (attachment_file_address, attachment_regist_user_no, notice_no) " +
            "values (#{attachmentFileAddress}, #{attachmentRegistUserNo}, #{noticeNo})")
    public void setAttachmentsByNoticeNo(TblAttachmentDTO tblAttachmentDTO);

    @Insert("INSERT INTO tbl_attachment (attachment_file_address, attachment_regist_user_no, tier_no) " +
            "values (#{attachmentFileAddress}, #{attachmentRegistUserNo}, #{tierNo})")
    public void setAttachmentsByTierNo(TblAttachmentDTO tblAttachmentDTO);


    @Update("UPDATE tbl_attachment SET " +
            "attachment_file_address = #{attachmentFileAddress} " +
            "WHERE subject_no = #{subjectNo}")
    void updateAttachmentBySubjectNo(TblAttachmentDTO attachmentDTO);

    @Update("UPDATE tbl_attachment SET " +
            "attachment_file_address = #{attachmentFileAddress} " +
            "WHERE inquiry_no = #{inquiryNo} AND attachment_regist_user_no = #{attachmentRegistUserNo}")
    void updateAttachmentByInquiryNo(TblAttachmentDTO attachmentDTO);


    @Update("UPDATE tbl_attachment SET " +
            "attachment_file_address = #{attachmentFileAddress} " +
            "WHERE attachment_user_no = #{attachmentUserNo} AND attachment_regist_user_no = #{attachmentRegistUserNo}")
    void updateAttachmentByAttachmentUserNo(TblAttachmentDTO attachmentDTO);

    @Update("UPDATE tbl_attachment SET " +
            "attachment_file_address = #{attachmentFileAddress} " +
            "WHERE notice_no = #{noticeNo} AND attachment_regist_user_no = #{attachmentRegistUserNo}")
    void updateAttachmentByNoticeNo(TblAttachmentDTO attachmentDTO);
    

    @Delete("DELETE FROM tbl_attachment WHERE subject_no = #{subjectNo}")
    void deleteAttachmentsBySubjectNo(long subjectNo);

    @Delete("DELETE FROM tbl_attachment WHERE inquiry_no = #{inquiryNo}")
    void deleteAttachmentsByInquiryNo(long inquiryNo);

    @Delete("DELETE FROM tbl_attachment WHERE attachment_user_no = #{attachmentUserNo}")
    void deleteAttachmentsByAttachmentUserNo(long attachmentUserNo);

    @Delete("DELETE FROM tbl_attachment WHERE notice_no = #{noticeNo}")
    void deleteAttachmentsByNoticeNo(long noticeNo);


    String getImgById(Long tierNo);
}
