package com.outsider.masterofprediction.dto;

import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class BettingInfoDTO {
    private long userNo;
    private double orderAmount;
    private String orderChoice;
    private double subjectTotalYesPoint;
    private double subjectTotalNoPoint;
    private String subjectFinishResult;
    private Date orderDate;
    private String subjectTitle;
    private long subjectNo;
    private Long currentPositionValue;
    private double profitPoints;
    private double lossPoints;
}
