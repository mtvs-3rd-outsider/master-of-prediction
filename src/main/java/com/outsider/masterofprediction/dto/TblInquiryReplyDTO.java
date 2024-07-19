package com.outsider.masterofprediction.dto;


public class TblInquiryReplyDTO {

  private long answerNo;
  private String answerContent;
  private java.sql.Timestamp answerTimestamp;
  private long answerInquiryNo;
  private long answerUserNo;
  private String answerTitle;

  public TblInquiryReplyDTO() {
  }

  public TblInquiryReplyDTO(long answerNo, String answerContent, java.sql.Timestamp answerTimestamp, long answerInquiryNo, long answerUserNo, String answerTitle) {
    this.answerNo = answerNo;
    this.answerContent = answerContent;
    this.answerTimestamp = answerTimestamp;
    this.answerInquiryNo = answerInquiryNo;
    this.answerUserNo = answerUserNo;
    this.answerTitle = answerTitle;
  }

  public long getAnswerNo() {
    return answerNo;
  }

  public void setAnswerNo(long answerNo) {
    this.answerNo = answerNo;
  }


  public String getAnswerContent() {
    return answerContent;
  }

  public void setAnswerContent(String answerContent) {
    this.answerContent = answerContent;
  }


  public java.sql.Timestamp getAnswerTimestamp() {
    return answerTimestamp;
  }

  public void setAnswerTimestamp(java.sql.Timestamp answerTimestamp) {
    this.answerTimestamp = answerTimestamp;
  }


  public long getAnswerInquiryNo() {
    return answerInquiryNo;
  }

  public void setAnswerInquiryNo(long answerInquiryNo) {
    this.answerInquiryNo = answerInquiryNo;
  }


  public long getAnswerUserNo() {
    return answerUserNo;
  }

  public void setAnswerUserNo(long answerUserNo) {
    this.answerUserNo = answerUserNo;
  }


  public String getAnswerTitle() {
    return answerTitle;
  }

  public void setAnswerTitle(String answerTitle) {
    this.answerTitle = answerTitle;
  }


  @Override
  public String toString() {
    return "TblInquiryReplyDTO" + '{' + 
            "answerNo='" + answerNo + '\'' + ',' +
            "answerContent='" + answerContent + '\'' + ',' +
            "answerTimestamp='" + answerTimestamp + '\'' + ',' +
            "answerInquiryNo='" + answerInquiryNo + '\'' + ',' +
            "answerUserNo='" + answerUserNo + '\'' + ',' +
            "answerTitle='" + answerTitle + '\'' +            '}';
  }
}
