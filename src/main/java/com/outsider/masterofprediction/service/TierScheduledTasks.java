package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.TblTierDTO;
import com.outsider.masterofprediction.dto.TblUserDTO;
import com.outsider.masterofprediction.mapper.TierMapper;
import com.outsider.masterofprediction.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TierScheduledTasks {
    private final TierMapper tiMapper;
    private final UserMapper userMapper;

    @Autowired
    public TierScheduledTasks(TierMapper tiMapper, UserMapper userMapper) {
        this.tiMapper = tiMapper;
        this.userMapper = userMapper;
    }

    @Scheduled(fixedRate = 300000) // 300000ms = 5분
    public void updateUserTier() {
        List<TblTierDTO> tiers = tiMapper.findAll();
        List<TblUserDTO> users = userMapper.findAllReturnTblUserDTO();

        int totalUsers = users.size();
        if(totalUsers==0)
        {
            return;
        }

        for (int i = 0; i < totalUsers; i++) {
            TblUserDTO user = users.get(i);
            Long tierNo = getTierForUser(i, totalUsers, tiers);
            if (tierNo == null){
                continue;
            }
            user.setTierNo(tierNo);
        }
        userMapper.bulkUpdateUserTiers(users); // 여기서 users는 List<UserDTO> 타입의 변수
    }

    private Long getTierForUser(int rank, int totalUsers, List<TblTierDTO> tiers) {
        int percentage = (int) Math.round((double)(rank + 1) / totalUsers * 100); // 현재 순위의 백분율 계산
        for (TblTierDTO tier : tiers) {
            if (percentage >= tier.getTierBegin() && percentage <= tier.getTierEnd()) {
                return tier.getTierNo();
            }
        }
        return null; // 티어가 없는 경우
    }

}
