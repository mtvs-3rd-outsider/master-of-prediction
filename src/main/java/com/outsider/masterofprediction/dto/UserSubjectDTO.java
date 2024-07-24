package com.outsider.masterofprediction.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserSubjectDTO {
    long userNo;
    long orderSubjectNo;
    String orderChoice;
}
