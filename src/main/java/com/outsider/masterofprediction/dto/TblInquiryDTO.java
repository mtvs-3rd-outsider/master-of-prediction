package com.outsider.masterofprediction.dto;


public class TblInquiryDTO {

  private long inquiryNo;
  private String inquiryTitle;
  private String inquiryContent;
  private java.sql.Timestamp inquiryTimestamp;
  private long inquiryReplyStatus;
  private long inquiryUserNo;

  public TblInquiryDTO() {
  }

  public TblInquiryDTO(long inquiryNo, String inquiryTitle, String inquiryContent, java.sql.Timestamp inquiryTimestamp, long inquiryReplyStatus, long inquiryUserNo) {
    this.inquiryNo = inquiryNo;
    this.inquiryTitle = inquiryTitle;
    this.inquiryContent = inquiryContent;
    this.inquiryTimestamp = inquiryTimestamp;
    this.inquiryReplyStatus = inquiryReplyStatus;
    this.inquiryUserNo = inquiryUserNo;
  }

  public long getInquiryNo() {
    return inquiryNo;
  }

  public void setInquiryNo(long inquiryNo) {
    this.inquiryNo = inquiryNo;
  }


  public String getInquiryTitle() {
    return inquiryTitle;
  }

  public void setInquiryTitle(String inquiryTitle) {
    this.inquiryTitle = inquiryTitle;
  }


  public String getInquiryContent() {
    return inquiryContent;
  }

  public void setInquiryContent(String inquiryContent) {
    this.inquiryContent = inquiryContent;
  }


  public java.sql.Timestamp getInquiryTimestamp() {
    return inquiryTimestamp;
  }

  public void setInquiryTimestamp(java.sql.Timestamp inquiryTimestamp) {
    this.inquiryTimestamp = inquiryTimestamp;
  }


  public long getInquiryReplyStatus() {
    return inquiryReplyStatus;
  }

  public void setInquiryReplyStatus(long inquiryReplyStatus) {
    this.inquiryReplyStatus = inquiryReplyStatus;
  }


  public long getInquiryUserNo() {
    return inquiryUserNo;
  }

  public void setInquiryUserNo(long inquiryUserNo) {
    this.inquiryUserNo = inquiryUserNo;
  }


  @Override
  public String toString() {
    return "TblInquiryDTO" + '{' + 
            "inquiryNo='" + inquiryNo + '\'' + ',' +
            "inquiryTitle='" + inquiryTitle + '\'' + ',' +
            "inquiryContent='" + inquiryContent + '\'' + ',' +
            "inquiryTimestamp='" + inquiryTimestamp + '\'' + ',' +
            "inquiryReplyStatus='" + inquiryReplyStatus + '\'' + ',' +
            "inquiryUserNo='" + inquiryUserNo + '\'' +            '}';
  }
}
