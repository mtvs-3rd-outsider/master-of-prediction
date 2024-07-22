package com.outsider.masterofprediction.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
@Getter
@Setter
@ToString
public class TblPointHistoryDTO {
    private long changeNO;
    private double changeAmount;
    private Timestamp changeTimestamp;
    private String changeReason;
    private long changeUserNo;

    public TblPointHistoryDTO() {
    }
}
