package com.outsider.masterofprediction.dto;


public class TblBettingOrderDTO {

    private long orderNo;
    private long orderAmount;
    private long orderChoice;
    private java.sql.Timestamp orderTimestamp;
    private long orderSubjectNo;
    private long orderUserNo;

    public TblBettingOrderDTO() {
    }

    public TblBettingOrderDTO(long orderNo, long orderAmount, long orderChoice, java.sql.Timestamp orderTimestamp, long orderSubjectNo, long orderUserNo) {
        this.orderNo = orderNo;
        this.orderAmount = orderAmount;
        this.orderChoice = orderChoice;
        this.orderTimestamp = orderTimestamp;
        this.orderSubjectNo = orderSubjectNo;
        this.orderUserNo = orderUserNo;
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }


    public long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(long orderAmount) {
        this.orderAmount = orderAmount;
    }


    public long getOrderChoice() {
        return orderChoice;
    }

    public void setOrderChoice(long orderChoice) {
        this.orderChoice = orderChoice;
    }


    public java.sql.Timestamp getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setOrderTimestamp(java.sql.Timestamp orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }


    public long getOrderSubjectNo() {
        return orderSubjectNo;
    }

    public void setOrderSubjectNo(long orderSubjectNo) {
        this.orderSubjectNo = orderSubjectNo;
    }


    public long getOrderUserNo() {
        return orderUserNo;
    }

    public void setOrderUserNo(long orderUserNo) {
        this.orderUserNo = orderUserNo;
    }


    @Override
    public String toString() {
        return "TblBettingOrderDTO" + '{' +
                "orderNo='" + orderNo + '\'' + ',' +
                "orderAmount='" + orderAmount + '\'' + ',' +
                "orderChoice='" + orderChoice + '\'' + ',' +
                "orderTimestamp='" + orderTimestamp + '\'' + ',' +
                "orderSubjectNo='" + orderSubjectNo + '\'' + ',' +
                "orderUserNo='" + orderUserNo + '\'' +            '}';
    }
}
