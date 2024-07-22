package com.outsider.masterofprediction.dto;

public class UserAttachmentDTO {

    private int userNo;
    private String userName;
    private int userPoint;

    private TblAttachmentDTO attachment;

    public UserAttachmentDTO() {}

    public UserAttachmentDTO(int userNo, String userName, int userPoint, TblAttachmentDTO attachment) {
        this.userNo = userNo;
        this.userName = userName;
        this.userPoint = userPoint;
        this.attachment = attachment;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(int userPoint) {
        this.userPoint = userPoint;
    }

    public TblAttachmentDTO getAttachment() {
        return attachment;
    }

    public void setAttachment(TblAttachmentDTO attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "UserAttachmentDTO{" +
                "userNo=" + userNo +
                ", userName=" + userName +
                ", userPoint=" + userPoint +
                ", attachment=" + attachment +
                '}';
    }
}
