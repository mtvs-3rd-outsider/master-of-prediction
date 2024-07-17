package com.outsider.masterofprediction.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TblAttachmentDTO {

    private long attachmentFileNo;
    private String attachmentFileAddress;
    private long attachmentRegistUserNo;
    private long answerNo;
    private long inquiryNo;
    private long attachmentUserNo;
    private long subjectNo;
    private long noticeNo;

    public TblAttachmentDTO() {
    }

    public TblAttachmentDTO(String attachmentFileAddress) {
        this.attachmentFileAddress = attachmentFileAddress;
    }

    public TblAttachmentDTO(long attachmentFileNo, String attachmentFileAddress, long attachmentRegistUserNo, long answerNo, long inquiryNo, long attachmentUserNo, long subjectNo, long noticeNo) {
        this.attachmentFileNo = attachmentFileNo;
        this.attachmentFileAddress = attachmentFileAddress;
        this.attachmentRegistUserNo = attachmentRegistUserNo;
        this.answerNo = answerNo;
        this.inquiryNo = inquiryNo;
        this.attachmentUserNo = attachmentUserNo;
        this.subjectNo = subjectNo;
        this.noticeNo = noticeNo;
    }
}
