package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.TblBettingOrderDTO;

public interface PointHistoryMapper {
    void insertHistoryByDTO(TblBettingOrderDTO bettingOrderDTO);
}
