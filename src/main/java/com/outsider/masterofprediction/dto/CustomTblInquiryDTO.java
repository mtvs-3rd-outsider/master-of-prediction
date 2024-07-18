package com.outsider.masterofprediction.dto;


import com.outsider.masterofprediction.dto.constatnt.InquiryReplyStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomTblInquiryDTO {

    private long inquiryNo;
    private String inquiryTitle;
    private String inquiryContent;
    private java.sql.Timestamp inquiryTimestamp;
    private InquiryReplyStatus inquiryReplyStatus;
    private long inquiryUserNo;

    public void setInquiryReplyStatus(long inquiryReplyStatus) {
        this.inquiryReplyStatus = InquiryReplyStatus.fromValue(inquiryReplyStatus);
    }
}
