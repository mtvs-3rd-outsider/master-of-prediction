package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.TblBettingOrderDTO;
import com.outsider.masterofprediction.dto.TblSubjectDTO;
import com.outsider.masterofprediction.dto.UserSubjectDTO;
import com.outsider.masterofprediction.dto.constatnt.SubjectStatus;
import com.outsider.masterofprediction.dto.constatnt.StringConstants;
import com.outsider.masterofprediction.dto.constatnt.SubjectStatus;
import com.outsider.masterofprediction.mapper.BettingOrderMapper;
import com.outsider.masterofprediction.mapper.SubjectMapper;
import com.outsider.masterofprediction.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubjectService {
    private final SubjectMapper subjectMapper;
    private final BettingOrderMapper bettingOrderMapper;
    private final UserMapper userMapper;

    @Autowired
    public SubjectService(SubjectMapper subjectMapper, BettingOrderMapper bettingOrderMapper, UserMapper userMapper) {
        this.subjectMapper = subjectMapper;
        this.bettingOrderMapper = bettingOrderMapper;
        this.userMapper = userMapper;
    }
    public boolean isSubjectFinishTimestampAfterCurrentTime(TblSubjectDTO tblSubjectDTO) {
        Timestamp subjectFinishTimestamp = tblSubjectDTO.getSubjectFinishTimestamp();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        return subjectFinishTimestamp.after(currentTime);
    }
    public TblSubjectDTO getSubjectBySubjectNo(long subjectNo) {
        return subjectMapper.getSubjectBySubjectNo(subjectNo);
    }
    public void updateSubjectStatus()
    {
       List<TblSubjectDTO> subjectDTOS  =  subjectMapper.getAllSubjects();
        for (TblSubjectDTO tblSubjectDTO : subjectDTOS) {
            if(isSubjectFinishTimestampAfterCurrentTime(tblSubjectDTO))
            {
                tblSubjectDTO.setSubjectStatus(SubjectStatus.SETTLEMENT.getValue());
                subjectMapper.updateSubject(tblSubjectDTO);
            }
        }
    }
    @Transactional
    public boolean BetSettlement(long subjectNo) {

        //결과 상품의 정보 가져오기
        TblSubjectDTO subjectDTO = subjectMapper.getSubjectById(subjectNo);
        if (subjectDTO != null && !SubjectStatus.SETTLEMENT.equals(SubjectStatus.fromValue(subjectDTO.getSubjectStatus()))) {
            // 해당 상품 승리에 베팅한 사람들의 정보 가져오기
            List<TblBettingOrderDTO> users = bettingOrderMapper.getUsersBySubjectNo(subjectNo);
            for (TblBettingOrderDTO user : users) {
                // 승리 여부에 따라 베팅 주문 가져오기
                String result = subjectDTO.getSubjectFinishResult();
                List<TblBettingOrderDTO> userBettingOrders = bettingOrderMapper.getBettingOrdersByUserSubjectDTO(new UserSubjectDTO(user.getOrderUserNo(), user.getOrderSubjectNo(), result));

                // 총 소모 포인트 계산
                long totalUserBettingPoint = userBettingOrders.stream().mapToLong(TblBettingOrderDTO::getOrderAmount).sum();

                // BigDecimal 객체로 변환
                BigDecimal totalUserBettingPointBD = new BigDecimal(totalUserBettingPoint);
                BigDecimal subjectTotalYesPointBD = new BigDecimal(subjectDTO.getSubjectTotalYesPoint());
                BigDecimal subjectTotalNoPointBD = new BigDecimal(subjectDTO.getSubjectTotalNoPoint());

                // 승리한 유저에게 수익포인트 + 베팅한 포인트를 돌려주기
                BigDecimal profitPoint = calculateProfitPoint(result, totalUserBettingPointBD, subjectTotalYesPointBD, subjectTotalNoPointBD);
                BigDecimal returnPoint = profitPoint.add(totalUserBettingPointBD);

                // returnPoint를 이용한 로직 추가
                userMapper.updateUserPointById(user.getOrderUserNo(),returnPoint);
            }
            return true;
        }
        else {
            return false;
        }
    }

    public boolean setSubjectFinishResult(long subjectNo, String result) {
        if (StringConstants.YES.equals(result) || StringConstants.NO.equals(result)){
            subjectMapper.setSubjectFinishResult(subjectNo, result, LocalDateTime.now());
            return true;
        }
        return false;
    }
    // 수익 포인트 계산 함수
    private BigDecimal calculateProfitPoint(String result, BigDecimal totalUserBettingPointBD, BigDecimal subjectTotalYesPointBD, BigDecimal subjectTotalNoPointBD) {
        BigDecimal profitPoint;
        BigDecimal rate;
        if ("YES".equals(result)) {
            rate = totalUserBettingPointBD.divide(subjectTotalYesPointBD, 2, BigDecimal.ROUND_HALF_UP);
            profitPoint = rate.multiply(subjectTotalNoPointBD);
        } else {
            rate = totalUserBettingPointBD.divide(subjectTotalNoPointBD, 2, BigDecimal.ROUND_HALF_UP);
            profitPoint = rate.multiply(subjectTotalYesPointBD);
        }
        return profitPoint;
    }
}
