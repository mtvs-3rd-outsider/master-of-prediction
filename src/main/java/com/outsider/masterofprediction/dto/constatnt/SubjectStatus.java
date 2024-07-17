package com.outsider.masterofprediction.dto.constatnt;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum SubjectStatus {
    IN_PROGRESS("진행중"),
    COMPLETED("종료"),
    ON_HOLD("보류"),
    SETTLEMENT("정산");

    private final String value;

    SubjectStatus(String value) {
        this.value = value;
    }

    public static SubjectStatus fromValue(String value) {
        for (SubjectStatus status : SubjectStatus.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        return null;
    }

}