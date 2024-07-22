package com.outsider.masterofprediction.dto;


import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TblAttachmentDTO {

    private long attachmentFileNo;
    private String attachmentFileAddress;
    private long attachmentRegistUserNo;
    private long answerNo;
    private long inquiryNo;
    private long attachmentUserNo;
    private long subjectNo;
    private long noticeNo;
    private long tierNo;
}
