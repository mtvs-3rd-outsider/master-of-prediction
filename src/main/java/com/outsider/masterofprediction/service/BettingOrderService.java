package com.outsider.masterofprediction.service;



import com.outsider.masterofprediction.dto.*;
import com.outsider.masterofprediction.mapper.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BettingOrderService {

    private BettingOrderMapper bettingOrderMapper;

    public BettingOrderService(BettingOrderMapper bettingOrderMapper) {
        this.bettingOrderMapper = bettingOrderMapper;
    }
    public List<BettingOrderDTO> getBettingOrdersByUserId(UserPaginationDTO userPaginationDTO) {
        return bettingOrderMapper.getBettingOrdersByUserId(userPaginationDTO);
    }

    public int getOrderCountByUserId(Long userId) {
        return bettingOrderMapper.getOrderCountByUserId(userId);
    }

}
