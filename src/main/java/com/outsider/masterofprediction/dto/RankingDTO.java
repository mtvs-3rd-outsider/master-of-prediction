package com.outsider.masterofprediction.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RankingDTO {
    private Long orderUserNo;
    private Long orderChoice;
    private long sum;

    public RankingDTO() {
    }

    public RankingDTO(Long orderUserNo, long orderChoice, long sum) {
        this.orderUserNo = orderUserNo;
        this.orderChoice = orderChoice;
        this.sum = sum;
    }
}
