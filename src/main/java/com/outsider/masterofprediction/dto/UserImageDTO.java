package com.outsider.masterofprediction.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class UserImageDTO {
    private Long userId;
    private String fileAddress;
}
