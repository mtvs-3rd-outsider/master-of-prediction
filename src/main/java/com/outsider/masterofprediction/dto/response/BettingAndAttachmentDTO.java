package com.outsider.masterofprediction.dto.response;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BettingAndAttachmentDTO {

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
    private String attachmentFileNo;
    private String attachmentFileAddress;
}
