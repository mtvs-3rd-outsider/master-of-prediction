package com.outsider.masterofprediction.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
@Getter
@Setter
@ToString
public class CommentReDTO {
    private long commnetNo;
    private String commentContent;
    private Timestamp commentTimestamp;
    private String commentUserName;
    private long replyNo;
    private String replyContent;
    private Timestamp replyTimestamp;
    private String replyUserName;

    public CommentReDTO() {
    }
}
