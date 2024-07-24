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
        Long totalPoints = bettingOrderMapper.getMonthTotalPointsByUser(userId);
        return totalPoints != null ? totalPoints : 0L;
    }

    public Long getMonthTotalProfitsByUserId(Long userId) {
        Long totalProfits = bettingOrderMapper.getMonthTotalProfitsByUserId(userId);
        return totalProfits != null ? totalProfits : 0L;
    }
    public Long getTotalPositionValueByUserId(Long userId) {
        Long totalPositionValue =  bettingOrderMapper.getTotalPositionValueByUserId(userId);
        return totalPositionValue != null ? totalPositionValue : 0L;
    }
    public List<BettingInfoDTO> getPositionValueByUserId(Long userId) {
        return bettingOrderMapper.getPositionValueByUserId(userId);
    }

    public List<BettingInfoDTO> getMonthProfitsByUserId(Long userId) {
        return bettingOrderMapper.getMonthProfitsByUserId(userId);
    }
    public Long getMonthTotalProfitRateByUserId(Long userId) {
        long totalProfits = getMonthTotalProfitsByUserId(userId);
        long totalPoints = getMonthTotalPointsByUser(userId);
        long totalProfitRate;
        if( totalPoints != 0) {
            totalProfitRate = totalProfits / totalPoints;
        }
        else
        {
            totalProfitRate = 0L;
        }
        return totalProfitRate;
    }


    public List<GraphDTO> getGraphByDTO(GraphDTO graphDTO) {
        return switch (graphDTO.getGraphTime()) {
            case "1H" -> bettingOrderMapper.getGraph1HByDTO(graphDTO);
            case "6H" -> bettingOrderMapper.getGraph6HByDTO(graphDTO);
            case "1D" -> bettingOrderMapper.getGraph1DByDTO(graphDTO);
            case "1W" -> bettingOrderMapper.getGraph1WByDTO(graphDTO);
            case "1M" -> bettingOrderMapper.getGraph1MByDTO(graphDTO);
            default -> bettingOrderMapper.getGraphAllByDTO(graphDTO);
        };
    }
}
