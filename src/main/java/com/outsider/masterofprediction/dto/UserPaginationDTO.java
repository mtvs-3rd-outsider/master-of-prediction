package com.outsider.masterofprediction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserPaginationDTO {
    private Long userId;
    private int start;
    private int itemsPerPage;
}
