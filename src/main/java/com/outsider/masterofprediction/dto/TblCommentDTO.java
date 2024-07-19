package com.outsider.masterofprediction.dto;


public class TblCommentDTO {

  private long commentNo;
  private String commentContent;
  private java.sql.Timestamp commentTimestamp;
  private long commentUserNo;
  private long commentSubjectNo;

  public TblCommentDTO() {
  }

  public TblCommentDTO(long commentNo, String commentContent, java.sql.Timestamp commentTimestamp, long commentUserNo, long commentSubjectNo) {
    this.commentNo = commentNo;
    this.commentContent = commentContent;
    this.commentTimestamp = commentTimestamp;
    this.commentUserNo = commentUserNo;
    this.commentSubjectNo = commentSubjectNo;
  }

  public long getCommentNo() {
    return commentNo;
  }

  public void setCommentNo(long commentNo) {
    this.commentNo = commentNo;
  }


  public String getCommentContent() {
    return commentContent;
  }

  public void setCommentContent(String commentContent) {
    this.commentContent = commentContent;
  }


  public java.sql.Timestamp getCommentTimestamp() {
    return commentTimestamp;
  }

  public void setCommentTimestamp(java.sql.Timestamp commentTimestamp) {
    this.commentTimestamp = commentTimestamp;
  }


  public long getCommentUserNo() {
    return commentUserNo;
  }

  public void setCommentUserNo(long commentUserNo) {
    this.commentUserNo = commentUserNo;
  }


  public long getCommentSubjectNo() {
    return commentSubjectNo;
  }

  public void setCommentSubjectNo(long commentSubjectNo) {
    this.commentSubjectNo = commentSubjectNo;
  }


  @Override
  public String toString() {
    return "TblCommentDTO" + '{' + 
            "commentNo='" + commentNo + '\'' + ',' +
            "commentContent='" + commentContent + '\'' + ',' +
            "commentTimestamp='" + commentTimestamp + '\'' + ',' +
            "commentUserNo='" + commentUserNo + '\'' + ',' +
            "commentSubjectNo='" + commentSubjectNo + '\'' +            '}';
  }
}
