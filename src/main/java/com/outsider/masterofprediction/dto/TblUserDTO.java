package com.outsider.masterofprediction.dto;


import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TblUserDTO {

  private long userNo;
  private String userName;
  private String userEmail;
  private String userPassword;
  private java.sql.Timestamp userJoinDate;
  private String userAuthority;
  private long userWithdrawalStatus;
  private double userPoint;
  private long tierNo;

}
