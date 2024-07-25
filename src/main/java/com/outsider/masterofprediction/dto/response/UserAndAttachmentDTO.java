package com.outsider.masterofprediction.dto.response;

import com.outsider.masterofprediction.utils.AttachmentFileAddressable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAndAttachmentDTO  implements AttachmentFileAddressable {
    private int userNo;
    private String userName;
    private int userPoint;
    private long attachmentFileNo;
    private String attachmentFileAddress;
    private long attachmentRegistUserNo;
    private long answerNo;
    private long inquiryNo;
    private long attachmentUserNo;
    private long subjectNo;
    private long noticeNo;
    private long tierNo;
}
