package com.outsider.masterofprediction.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
@Getter
@Setter
@ToString
public class ActiveDTO {
    private String name;
    private long amount;
    private String choice;
    private java.sql.Timestamp activeTimestamp;
    private long userNo;
    private String imgUrl;

    public ActiveDTO() {
    }

}
