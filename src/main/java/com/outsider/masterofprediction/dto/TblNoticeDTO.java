package com.outsider.masterofprediction.dto;


public class TblNoticeDTO {

  private long noticeNo;
  private String noticeTitle;
  private String noticeContent;
  private java.sql.Timestamp noticeTimestamp;
  private long noticeUserNo;

  public TblNoticeDTO() {
  }

  public TblNoticeDTO(long noticeNo, String noticeTitle, String noticeContent, java.sql.Timestamp noticeTimestamp, long noticeUserNo) {
    this.noticeNo = noticeNo;
    this.noticeTitle = noticeTitle;
    this.noticeContent = noticeContent;
    this.noticeTimestamp = noticeTimestamp;
    this.noticeUserNo = noticeUserNo;
  }

  public long getNoticeNo() {
    return noticeNo;
  }

  public void setNoticeNo(long noticeNo) {
    this.noticeNo = noticeNo;
  }


  public String getNoticeTitle() {
    return noticeTitle;
  }

  public void setNoticeTitle(String noticeTitle) {
    this.noticeTitle = noticeTitle;
  }


  public String getNoticeContent() {
    return noticeContent;
  }

  public void setNoticeContent(String noticeContent) {
    this.noticeContent = noticeContent;
  }


  public java.sql.Timestamp getNoticeTimestamp() {
    return noticeTimestamp;
  }

  public void setNoticeTimestamp(java.sql.Timestamp noticeTimestamp) {
    this.noticeTimestamp = noticeTimestamp;
  }


  public long getNoticeUserNo() {
    return noticeUserNo;
  }

  public void setNoticeUserNo(long noticeUserNo) {
    this.noticeUserNo = noticeUserNo;
  }


  @Override
  public String toString() {
    return "TblNoticeDTO" + '{' + 
            "noticeNo='" + noticeNo + '\'' + ',' +
            "noticeTitle='" + noticeTitle + '\'' + ',' +
            "noticeContent='" + noticeContent + '\'' + ',' +
            "noticeTimestamp='" + noticeTimestamp + '\'' + ',' +
            "noticeUserNo='" + noticeUserNo + '\'' +            '}';
  }
}
