package com.outsider.masterofprediction.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
@Getter
@Setter
@ToString
public class TblReplyDTO {
    private long replyNo;
    private String replyContent;
    private Timestamp replyTimestamp;
    private long replyUserNo;
    private long replyCommentNo;

    public TblReplyDTO() {
    }
}
