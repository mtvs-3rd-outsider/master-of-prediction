package com.outsider.masterofprediction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserWithdrawalStatusDTO {
    private Long userId;
    private boolean withdrawal;


}
