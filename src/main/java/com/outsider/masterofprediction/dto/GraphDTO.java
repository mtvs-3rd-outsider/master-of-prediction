package com.outsider.masterofprediction.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GraphDTO {
    private String graphTime;
    private long subjectNo;
    private String displayTime;
    private long yesRate;
    private long noRate;
    public GraphDTO() {
    }

}