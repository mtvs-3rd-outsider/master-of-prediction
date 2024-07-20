package com.outsider.masterofprediction.service;


import com.outsider.masterofprediction.dto.*;
import com.outsider.masterofprediction.mapper.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BettingOrderService {

    private final BettingOrderMapper bettingOrderMapper;

    public BettingOrderService(BettingOrderMapper bettingOrderMapper) {
        this.bettingOrderMapper = bettingOrderMapper;
    }
    public List<BettingOrderDTO> getBettingOrdersByUserId(UserPaginationDTO userPaginationDTO) {
        return bettingOrderMapper.getBettingOrdersByUserId(userPaginationDTO);
    }

    public int getOrderCountByUserId(Long userId) {
        return bettingOrderMapper.getOrderCountByUserId(userId);
    }

    public List<ActiveDTO> getBettingOrdersBySubjectNo(Long subjectNo) {
        return bettingOrderMapper.getBettingOrdersBySubjectNo(subjectNo);
    }

    public List<RankingDTO> getRankingBySubjectNo(Long subjectNo) {
        return bettingOrderMapper.getRankingBySubjectNo(subjectNo);
    }

    public Long getMonthTotalPointsByUser(Long userId) {
        return bettingOrderMapper.getMonthTotalPointsByUser(userId);
    }

    public Long getMonthTotalProfitsByUser(Long userId) {
        return bettingOrderMapper.getMonthTotalProfitsByUserId(userId);
    }
    public Long getTotalPositionValueByUserId(Long userId) {
        return bettingOrderMapper.getTotalPositionValueByUserId(userId);
    }
    public List<BettingInfoDTO> getPositionValueByUserId(Long userId) {
        return bettingOrderMapper.getPositionValueByUserId(userId);
    }

    public List<BettingInfoDTO> getMonthProfitsByUserId(Long userId) {
        return bettingOrderMapper.getMonthProfitsByUserId(userId);
    }


}
