package com.outsider.masterofprediction.dto;


import java.sql.Timestamp;

public class InquiryDetailDTO {
    private String inquiryTitle;
    private String inquiryContent;
    private Timestamp inquiryTimestamp;
    private String answerContent;
    private String answerTitle;
    private Timestamp answerTimestamp;

    public InquiryDetailDTO() {
    }

    public InquiryDetailDTO(String inquiryTitle, String inquiryContent, Timestamp inquiryTimestamp, String answerContent, String answerTitle, Timestamp answerTimestamp) {
        this.inquiryTitle = inquiryTitle;
        this.inquiryContent = inquiryContent;
        this.inquiryTimestamp = inquiryTimestamp;
        this.answerContent = answerContent;
        this.answerTitle = answerTitle;
        this.answerTimestamp = answerTimestamp;
    }

    @Override
    public String toString() {
        return "InquiryDetailDTO{" +
                "inquiryTitle='" + inquiryTitle + '\'' +
                ", inquiryContent='" + inquiryContent + '\'' +
                ", inquiryTimestamp=" + inquiryTimestamp +
                ", answerContent='" + answerContent + '\'' +
                ", answerTitle='" + answerTitle + '\'' +
                ", answerTimestamp=" + answerTimestamp +
                '}';
    }

    // Getters and Setters
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

    public Timestamp getInquiryTimestamp() {
        return inquiryTimestamp;
    }

    public void setInquiryTimestamp(Timestamp inquiryTimestamp) {
        this.inquiryTimestamp = inquiryTimestamp;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getAnswerTitle() {
        return answerTitle;
    }

    public void setAnswerTitle(String answerTitle) {
        this.answerTitle = answerTitle;
    }

    public Timestamp getAnswerTimestamp() {
        return answerTimestamp;
    }

    public void setAnswerTimestamp(Timestamp answerTimestamp) {
        this.answerTimestamp = answerTimestamp;
    }
}
