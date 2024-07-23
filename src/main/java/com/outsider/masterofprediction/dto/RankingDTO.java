package com.outsider.masterofprediction.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class RankingDTO {
    private int userNo;
    private String name;
    private String choice;

    private long sum;

    public RankingDTO() {
    }

    public RankingDTO(String name, String choice, long sum) {
        this.name = name;
        this.choice = choice;
        this.sum = sum;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "RankingDTO{" +
                "userNo=" + userNo +
                ", name='" + name + '\'' +
                ", choice='" + choice + '\'' +
                ", sum=" + sum +
                '}';
    }
}
