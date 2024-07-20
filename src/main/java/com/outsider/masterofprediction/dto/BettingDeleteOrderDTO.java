package com.outsider.masterofprediction.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class BettingDeleteOrderDTO {

    private Long userId;
    private BigDecimal point;

    public BettingDeleteOrderDTO(Long userId, BigDecimal point) {
        this.userId = userId;
        this.point = point;
    }
}
