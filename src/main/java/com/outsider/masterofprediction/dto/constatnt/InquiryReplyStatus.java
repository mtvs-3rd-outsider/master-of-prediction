package com.outsider.masterofprediction.dto.constatnt;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum InquiryReplyStatus {
    NO(0L),
    YES(1L);

    private final long value;

    InquiryReplyStatus(long value) {
        this.value = value;
    }

    public static InquiryReplyStatus fromValue(long value) {
        for (InquiryReplyStatus status : InquiryReplyStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }

    public static String statusToString(long value) {
        if (value == 1L){
            return "답변완료";
        }
        return "문의대기";
    }
}