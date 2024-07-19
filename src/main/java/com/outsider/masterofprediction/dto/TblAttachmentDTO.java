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

    public long getAttachmentFileNo() {
        return attachmentFileNo;
    }

    public void setAttachmentFileNo(long attachmentFileNo) {
        this.attachmentFileNo = attachmentFileNo;
    }

    public String getAttachmentFileAddress() {
        return attachmentFileAddress;
    }

    public void setAttachmentFileAddress(String attachmentFileAddress) {
        this.attachmentFileAddress = attachmentFileAddress;
    }

    public long getAttachmentRegistUserNo() {
        return attachmentRegistUserNo;
    }

    public void setAttachmentRegistUserNo(long attachmentRegistUserNo) {
        this.attachmentRegistUserNo = attachmentRegistUserNo;
    }

    public long getAnswerNo() {
        return answerNo;
    }

    public void setAnswerNo(long answerNo) {
        this.answerNo = answerNo;
    }

    public long getInquiryNo() {
        return inquiryNo;
    }

    public void setInquiryNo(long inquiryNo) {
        this.inquiryNo = inquiryNo;
    }

    public long getAttachmentUserNo() {
        return attachmentUserNo;
    }

    public void setAttachmentUserNo(long attachmentUserNo) {
        this.attachmentUserNo = attachmentUserNo;
    }

    public long getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(long subjectNo) {
        this.subjectNo = subjectNo;
    }

    public long getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(long noticeNo) {
        this.noticeNo = noticeNo;
    }

    @Override
    public String toString() {
        return "TblAttachmentDTO{" +
                "attachmentFileNo=" + attachmentFileNo +
                ", attachmentFileAddress='" + attachmentFileAddress + '\'' +
                ", attachmentRegistUserNo=" + attachmentRegistUserNo +
                ", answerNo=" + answerNo +
                ", inquiryNo=" + inquiryNo +
                ", attachmentUserNo=" + attachmentUserNo +
                ", subjectNo=" + subjectNo +
                ", noticeNo=" + noticeNo +
                '}';
    }
}
