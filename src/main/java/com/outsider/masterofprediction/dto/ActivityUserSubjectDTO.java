package com.outsider.masterofprediction.dto;

import com.outsider.masterofprediction.utils.AttachmentFileAddressable;

import java.sql.Timestamp;

public class ActivityUserSubjectDTO {

    private int orderNo;
    private int orderAmount;
    private String orderChoice;
    private java.sql.Timestamp orderTimestamp;

    private TblUserDTO user;
    private TblSubjectDTO subject;
    private TblAttachmentDTO attachmentUser;
    private TblAttachmentDTO attachmentSubject;

    public ActivityUserSubjectDTO() {}

    public ActivityUserSubjectDTO(int orderNo, int orderAmount, String orderChoice, Timestamp orderTimestamp, TblUserDTO user, TblSubjectDTO subject, TblAttachmentDTO attachmentUser, TblAttachmentDTO attachmentSubject) {
        this.orderNo = orderNo;
        this.orderAmount = orderAmount;
        this.orderChoice = orderChoice;
        this.orderTimestamp = orderTimestamp;
        this.user = user;
        this.subject = subject;
        this.attachmentUser = attachmentUser;
        this.attachmentSubject = attachmentSubject;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderChoice() {
        return orderChoice;
    }

    public void setOrderChoice(String orderChoice) {
        this.orderChoice = orderChoice;
    }

    public Timestamp getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setOrderTimestamp(Timestamp orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }

    public TblUserDTO getUser() {
        return user;
    }

    public void setUser(TblUserDTO user) {
        this.user = user;
    }

    public TblSubjectDTO getSubject() {
        return subject;
    }

    public void setSubject(TblSubjectDTO subject) {
        this.subject = subject;
    }

    public TblAttachmentDTO getAttachmentUser() {
        if(attachmentUser == null) {
            attachmentUser = new TblAttachmentDTO(0, "logo2.png", 0, 0, 0, 0, 0, 0, 0);
        }

        return attachmentUser;
    }

    public void setAttachmentUser(TblAttachmentDTO attachmentUser) {
        this.attachmentUser = attachmentUser;
    }

    public TblAttachmentDTO getAttachmentSubject() {
        if(attachmentSubject == null) {
            attachmentSubject = new TblAttachmentDTO(0, "logo2.png", 0, 0, 0, 0, 0, 0, 0);
        }

        return attachmentSubject;
    }

    public void setAttachmentSubject(TblAttachmentDTO attachmentSubject) {
        this.attachmentSubject = attachmentSubject;
    }

    @Override
    public String toString() {
        return "ActivityUserSubjectDTO{" +
                "orderNo=" + orderNo +
                ", orderAmount=" + orderAmount +
                ", orderChoice='" + orderChoice + '\'' +
                ", orderTimestamp=" + orderTimestamp +
                ", \n user=" + user +
                ", \n subject=" + subject +
                ", \n attachmentUser=" + attachmentUser +
                ", \n attachmentSubject=" + attachmentSubject +
                '}';
    }
}
