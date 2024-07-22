package com.outsider.masterofprediction.dto;


import java.sql.Timestamp;

public class TblUserDTO {

  private long userNo;
  private String userName;
  private String userEmail;
  private String userPassword;
  private java.sql.Timestamp userJoinDate;
  private String userAuthority;
  private long userWithdrawalStatus;
  private double userPoint;

  public TblUserDTO() {
  }

  public TblUserDTO(long userNo, String userName, String userEmail, String userPassword, Timestamp userJoinDate, String userAuthority, long userWithdrawalStatus, double userPoint) {
    this.userNo = userNo;
    this.userName = userName;
    this.userEmail = userEmail;
    this.userPassword = userPassword;
    this.userJoinDate = userJoinDate;
    this.userAuthority = userAuthority;
    this.userWithdrawalStatus = userWithdrawalStatus;
    this.userPoint = userPoint;
  }

  public long getUserNo() {
    return userNo;
  }

  public void setUserNo(long userNo) {
    this.userNo = userNo;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  public java.sql.Timestamp getUserJoinDate() {
    return userJoinDate;
  }

  public void setUserJoinDate(java.sql.Timestamp userJoinDate) {
    this.userJoinDate = userJoinDate;
  }


  public String getUserAuthority() {
    return userAuthority;
  }

  public void setUserAuthority(String userAuthority) {
    this.userAuthority = userAuthority;
  }


  public long getUserWithdrawalStatus() {
    return userWithdrawalStatus;
  }

  public void setUserWithdrawalStatus(long userWithdrawalStatus) {
    this.userWithdrawalStatus = userWithdrawalStatus;
  }


  public double getUserPoint() {
    return userPoint;
  }

  public void setUserPoint(double userPoint) {
    this.userPoint = userPoint;
  }


  @Override
  public String toString() {
    return "TblUserDTO" + '{' + 
            "userNo='" + userNo + '\'' + ',' +
            "userName='" + userName + '\'' + ',' +
            "userEmail='" + userEmail + '\'' + ',' +
            "userPassword='" + userPassword + '\'' + ',' +
            "userJoinDate='" + userJoinDate + '\'' + ',' +
            "userAuthority='" + userAuthority + '\'' + ',' +
            "userWithdrawalStatus='" + userWithdrawalStatus + '\'' + ',' +
            "userPoint='" + userPoint + '\'' +            '}';
  }
}
