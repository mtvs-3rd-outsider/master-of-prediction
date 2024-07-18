package com.outsider.masterofprediction.dto;

public class BettingOrderDTO {
    private String subjectTitle;
    private String orderChoice;
    private String subjectStatus;
    private String subjectFinishResult;

    // Getters and Setters

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getOrderChoice() {
        return orderChoice;
    }

    public void setOrderChoice(String orderChoice) {
        this.orderChoice = orderChoice;
    }

    public String getSubjectStatus() {
        return subjectStatus;
    }

    public void setSubjectStatus(String subjectStatus) {
        this.subjectStatus = subjectStatus;
    }

    public String getSubjectFinishResult() {
        return subjectFinishResult;
    }

    public void setSubjectFinishResult(String subjectFinishResult) {
        this.subjectFinishResult = subjectFinishResult;
    }

    public BettingOrderDTO(String subjectTitle, String orderChoice, String subjectStatus, String subjectFinishResult) {
        this.subjectTitle = subjectTitle;
        this.orderChoice = orderChoice;
        this.subjectStatus = subjectStatus;
        this.subjectFinishResult = subjectFinishResult;
    }

    @Override
    public String toString() {
        return "BettingOrderDTO{" +
                "subjectTitle='" + subjectTitle + '\'' +
                ", orderChoice='" + orderChoice + '\'' +
                ", subjectStatus='" + subjectStatus + '\'' +
                ", subjectFinishResult='" + subjectFinishResult + '\'' +
                '}';
    }
}
