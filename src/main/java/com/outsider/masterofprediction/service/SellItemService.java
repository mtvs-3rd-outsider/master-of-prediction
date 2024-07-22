package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.TblBettingOrderDTO;
import com.outsider.masterofprediction.mapper.BettingOrderMapper;
import com.outsider.masterofprediction.mapper.PointHistoryMapper;
import com.outsider.masterofprediction.mapper.SubjectMapper;
import com.outsider.masterofprediction.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SellItemService {
    private final BettingOrderMapper bettingOrderMapper;
    private final UserMapper userMapper;
    private final SubjectMapper subjectMapper;
    private final PointHistoryMapper pointHistoryMapper;

    @Autowired
    public SellItemService(BettingOrderMapper bettingOrderMapper, UserMapper userMapper, SubjectMapper subjectMapper, PointHistoryMapper pointHistoryMapper) {
        this.bettingOrderMapper = bettingOrderMapper;
        this.userMapper = userMapper;
        this.subjectMapper = subjectMapper;
        this.pointHistoryMapper = pointHistoryMapper;
    }

    @Transactional
    public void sellItemByDTO(TblBettingOrderDTO bettingOrderDTO) {
        int sum;

        if(bettingOrderDTO.getOrderChoice().equals("YES")){
            bettingOrderDTO.setOrderChoice("Yes");
            //해당 게임의 로그인 중인 아이디의 Yes구매를 활동내역으로 부터 가져와 합계를 얻습니다.
            sum=bettingOrderMapper.getYesSumPointByDTO(bettingOrderDTO);
            if(bettingOrderDTO.getOrderAmount()<=sum){
                userMapper.updateUserPointBySum(bettingOrderDTO);
            }else{
                //예외 발생 "보유한 YES가 부족합니다"
                throw new IllegalStateException("보유한 YES포인트가 부족합니다.");
            }
            bettingOrderDTO.setOrderAmount(-bettingOrderDTO.getOrderAmount());
            bettingOrderMapper.insertBettingOrderByDTO(bettingOrderDTO);
            subjectMapper.updateTotalPointByDTO(bettingOrderDTO);
            pointHistoryMapper.insertHistoryByDTO(bettingOrderDTO);

        }else if(bettingOrderDTO.getOrderChoice().equals("NO")){
            bettingOrderDTO.setOrderChoice("No");
            //해당 게임의 로그인 중인 아이디의 Yes구매를 활동내역으로 부터 가져와 합계를 얻습니다.
            sum=bettingOrderMapper.getNoSumPointByDTO(bettingOrderDTO);
            if(bettingOrderDTO.getOrderAmount()<=sum){
                userMapper.updateUserPointBySum(bettingOrderDTO);
            }else{
                //예외 발생 "보유한 NO가 부족합니다"
                throw new IllegalStateException("보유한 NO포인트가 부족합니다.");
            }
            bettingOrderDTO.setOrderAmount(-bettingOrderDTO.getOrderAmount());
            bettingOrderMapper.insertBettingOrderByDTO(bettingOrderDTO);
            subjectMapper.updateTotalPointByDTO(bettingOrderDTO);
            pointHistoryMapper.insertHistoryByDTO(bettingOrderDTO);
        }
    }
}
