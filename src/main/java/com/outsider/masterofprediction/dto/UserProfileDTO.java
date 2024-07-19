package com.outsider.masterofprediction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserProfileDTO {
    private String userName;
    private String userPassword;
    private Long userId;
}
