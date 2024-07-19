package com.outsider.masterofprediction.dto;

public class TblUserAndAttachmentDTO {

    private long userNo;
    private String userName;
    private double userPoint;

    private long attachmentFileNo;
    private String attachmentFileAddress;
    private long attachmentRegistUserNo;
    private long attachmentUserNo;

    public TblUserAndAttachmentDTO() {}

    public TblUserAndAttachmentDTO(long userNo, String userName, double userPoint, long attachmentFileNo, String attachmentFileAddress, long attachmentRegistUserNo, long attachmentUserNo) {
        this.userNo = userNo;
        this.userName = userName;
        this.userPoint = userPoint;
        this.attachmentFileNo = attachmentFileNo;
        this.attachmentFileAddress = attachmentFileAddress;
        this.attachmentRegistUserNo = attachmentRegistUserNo;
        this.attachmentUserNo = attachmentUserNo;
    }

    public long getUserNo() {
        return userNo;
    }

    public void setUserNo(long userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(double userPoint) {
        this.userPoint = userPoint;
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

    public long getAttachmentUserNo() {
        return attachmentUserNo;
    }

    public void setAttachmentUserNo(long attachmentUserNo) {
        this.attachmentUserNo = attachmentUserNo;
    }

    @Override
    public String toString() {
        return "TblUserAndAttachmentDTO{" +
                "userNo=" + userNo +
                ", userName='" + userName + '\'' +
                ", userPoint=" + userPoint +
                ", attachmentFileNo=" + attachmentFileNo +
                ", attachmentFileAddress='" + attachmentFileAddress + '\'' +
                ", attachmentRegistUserNo=" + attachmentRegistUserNo +
                ", attachmentUserNo=" + attachmentUserNo +
                '}';
    }
}
