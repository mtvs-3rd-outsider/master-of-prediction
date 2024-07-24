package com.outsider.masterofprediction.dto;

import com.outsider.masterofprediction.utils.AttachmentFileAddressable;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPageSubjectDTO implements AttachmentFileAddressable {
    private Long subjectNo;
    private String subjectTitle;
    private String subjectStatus;
    private String totalPoints;
    private String yesPercentage;
    private String noPercentage;
    private int totalCommentsReplies;
    private String attachmentFileAddress;
}
