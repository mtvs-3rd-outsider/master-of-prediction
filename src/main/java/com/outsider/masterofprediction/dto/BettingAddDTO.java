package com.outsider.masterofprediction.dto;

import java.sql.Timestamp;

public class BettingAddDTO {

    private long subjectNo;
    private String subjectTitle;
    private long subjectTotalYesPoint;
    private String subjectStatus;
    private java.sql.Timestamp subjectRegisterTimestamp;
    private java.sql.Timestamp subjectFinishTimestamp;
    private java.sql.Timestamp subjectSettlementTimestamp;
    private long subjectCategoryNo;
    private long subjectRegisterUserNo;
    private String subjectFinishResult;
    private long subjectTotalNoPoint;

    public BettingAddDTO() {
    }

    public BettingAddDTO(long subjectNo, String subjectTitle, long subjectTotalYesPoint, String subjectStatus, Timestamp subjectRegisterTimestamp, Timestamp subjectFinishTimestamp, Timestamp subjectSettlementTimestamp, long subjectCategoryNo, long subjectRegisterUserNo, String subjectFinishResult, long subjectTotalNoPoint) {
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

    public long getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(long subjectNo) {
        this.subjectNo = subjectNo;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public long getSubjectTotalYesPoint() {
        return subjectTotalYesPoint;
    }

    public void setSubjectTotalYesPoint(long subjectTotalYesPoint) {
        this.subjectTotalYesPoint = subjectTotalYesPoint;
    }

    public String getSubjectStatus() {
        return subjectStatus;
    }

    public void setSubjectStatus(String subjectStatus) {
        this.subjectStatus = subjectStatus;
    }

    public Timestamp getSubjectRegisterTimestamp() {
        return subjectRegisterTimestamp;
    }

    public void setSubjectRegisterTimestamp(Timestamp subjectRegisterTimestamp) {
        this.subjectRegisterTimestamp = subjectRegisterTimestamp;
    }

    public Timestamp getSubjectFinishTimestamp() {
        return subjectFinishTimestamp;
    }

    public void setSubjectFinishTimestamp(Timestamp subjectFinishTimestamp) {
        this.subjectFinishTimestamp = subjectFinishTimestamp;
    }

    public Timestamp getSubjectSettlementTimestamp() {
        return subjectSettlementTimestamp;
    }

    public void setSubjectSettlementTimestamp(Timestamp subjectSettlementTimestamp) {
        this.subjectSettlementTimestamp = subjectSettlementTimestamp;
    }

    public long getSubjectCategoryNo() {
        return subjectCategoryNo;
    }

    public void setSubjectCategoryNo(long subjectCategoryNo) {
        this.subjectCategoryNo = subjectCategoryNo;
    }

    public long getSubjectRegisterUserNo() {
        return subjectRegisterUserNo;
    }

    public void setSubjectRegisterUserNo(long subjectRegisterUserNo) {
        this.subjectRegisterUserNo = subjectRegisterUserNo;
    }

    public String getSubjectFinishResult() {
        return subjectFinishResult;
    }

    public void setSubjectFinishResult(String subjectFinishResult) {
        this.subjectFinishResult = subjectFinishResult;
    }

    public long getSubjectTotalNoPoint() {
        return subjectTotalNoPoint;
    }

    public void setSubjectTotalNoPoint(long subjectTotalNoPoint) {
        this.subjectTotalNoPoint = subjectTotalNoPoint;
    }

    @Override
    public String toString() {
        return "BettingAddDTO{" +
                "subjectNo=" + subjectNo +
                ", subjectTitle='" + subjectTitle + '\'' +
                ", subjectTotalYesPoint=" + subjectTotalYesPoint +
                ", subjectStatus='" + subjectStatus + '\'' +
                ", subjectRegisterTimestamp=" + subjectRegisterTimestamp +
                ", subjectFinishTimestamp=" + subjectFinishTimestamp +
                ", subjectSettlementTimestamp=" + subjectSettlementTimestamp +
                ", subjectCategoryNo=" + subjectCategoryNo +
                ", subjectRegisterUserNo=" + subjectRegisterUserNo +
                ", subjectFinishResult='" + subjectFinishResult + '\'' +
                ", subjectTotalNoPoint=" + subjectTotalNoPoint +
                '}';
    }
}
