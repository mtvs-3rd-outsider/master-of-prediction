package com.outsider.masterofprediction.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class TblSubjectDTO {

    private long subjectNo;
    private String subjectTitle;
    private long subjectTotalYesPoint;
    private String subjectStatus;
    private Timestamp subjectRegisterTimestamp;
    private Timestamp subjectFinishTimestamp;
    private Timestamp subjectSettlementTimestamp;
    private long subjectCategoryNo;
    private long subjectRegisterUserNo;
    private String subjectFinishResult;
    private long subjectTotalNoPoint;

    public TblSubjectDTO() {}

    public TblSubjectDTO(long subjectNo, String subjectTitle, long subjectTotalYesPoint, String subjectStatus, Timestamp subjectRegisterTimestamp, Timestamp subjectFinishTimestamp, Timestamp subjectSettlementTimestamp, long subjectCategoryNo, long subjectRegisterUserNo, String subjectFinishResult, long subjectTotalNoPoint) {
        this.subjectNo = subjectNo;
        this.subjectTitle = subjectTitle;
        this.subjectTotalYesPoint = subjectTotalYesPoint;
        this.subjectStatus = subjectStatus;
        this.subjectRegisterTimestamp = subjectRegisterTimestamp;
        this.subjectFinishTimestamp = subjectFinishTimestamp;
        this.subjectSettlementTimestamp = subjectSettlementTimestamp;
        this.subjectCategoryNo = subjectCategoryNo;
        this.subjectRegisterUserNo = subjectRegisterUserNo;
        this.subjectFinishResult = subjectFinishResult;
        this.subjectTotalNoPoint = subjectTotalNoPoint;
    }
}
