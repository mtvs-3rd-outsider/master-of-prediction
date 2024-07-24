package com.outsider.masterofprediction.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class RankingDTO {
    private String name;
    private String choice;
    private long sum;
    private long no;
    private String imgUrl;

    public RankingDTO() {
    }

    public RankingDTO(String name, String choice, long sum, long no) {
        this.name = name;
        this.choice = choice;
        this.sum = sum;
        this.no = no;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
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
                "name='" + name + '\'' +
                ", choice='" + choice + '\'' +
                ", sum=" + sum +
                ", no=" + no +
                '}';
    }
}
