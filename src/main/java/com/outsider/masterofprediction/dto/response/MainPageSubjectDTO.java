package com.outsider.masterofprediction.dto.response;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MainPageSubjectDTO {
    private Long subjectNo;
    private String subjectTitle;
    private String subjectStatus;
    private String totalPoints;
    private String yesPercentage;
    private String noPercentage;
    private int totalCommentsReplies;
    private String attachmentFileAddress;
}
