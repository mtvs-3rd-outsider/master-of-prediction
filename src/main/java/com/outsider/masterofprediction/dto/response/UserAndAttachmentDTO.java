package com.outsider.masterofprediction.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAndAttachmentDTO {
    private int userNo;
    private String userName;
    private int userPoint;
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
