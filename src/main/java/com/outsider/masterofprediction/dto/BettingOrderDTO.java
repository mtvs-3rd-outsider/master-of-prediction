package com.outsider.masterofprediction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class BettingOrderDTO {
    private String subjectTitle;
    private String orderChoice;
    private String subjectStatus;
    private String subjectFinishResult;
    private Long subjectNo;

    // Getters and Setters

}
