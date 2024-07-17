package com.outsider.masterofprediction.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Setter
@Getter
public class Notice {
    private Long id;
    private String title;
    private String content;
    private Timestamp timestamp;
    private Long userId;
}
