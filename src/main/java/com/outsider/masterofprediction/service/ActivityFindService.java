package com.outsider.masterofprediction.service;


import com.outsider.masterofprediction.dto.ActivityUserSubjectDTO;
import com.outsider.masterofprediction.dto.TblBettingOrderDTO;
import com.outsider.masterofprediction.mapper.BettingOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityFindService {

    private BettingOrderMapper bettingOrderMapper;

    @Autowired
    public ActivityFindService(BettingOrderMapper bettingOrderMapper){
        this.bettingOrderMapper = bettingOrderMapper;
    }

    public List<ActivityUserSubjectDTO> findActivity() {
        return bettingOrderMapper.findActivity();
    }
}
