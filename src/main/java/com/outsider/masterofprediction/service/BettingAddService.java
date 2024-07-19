package com.outsider.masterofprediction.service;


import com.outsider.masterofprediction.dto.BettingAddDTO;
import com.outsider.masterofprediction.mapper.BettingAddMapper;
import org.springframework.stereotype.Service;

@Service
public class BettingAddService {
    private final BettingAddMapper bettingAddMapper;

    public BettingAddService(BettingAddMapper bettingAddMapper) {
        this.bettingAddMapper = bettingAddMapper;
    }


    public void create(BettingAddDTO bettingAddDTO) {
        bettingAddMapper.create(bettingAddDTO);
    }
}
