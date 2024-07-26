package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.RankingDTO;
import com.outsider.masterofprediction.dto.TblBettingOrderDTO;
import com.outsider.masterofprediction.dto.TblSubjectDTO;
import com.outsider.masterofprediction.dto.UserSubjectDTO;
import com.outsider.masterofprediction.dto.constatnt.SubjectStatus;
import com.outsider.masterofprediction.dto.constatnt.StringConstants;
import com.outsider.masterofprediction.mapper.BettingOrderMapper;
import com.outsider.masterofprediction.mapper.SubjectMapper;
import com.outsider.masterofprediction.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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
    public boolean isSubjectSubjectSettlementTimestampAfterCurrentTime(TblSubjectDTO tblSubjectDTO) {
        Timestamp subjectSettlementTimestamp = tblSubjectDTO.getSubjectSettlementTimestamp();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        return subjectSettlementTimestamp.before(currentTime);
    }
    public TblSubjectDTO getSubjectBySubjectNo(long subjectNo) {
        return subjectMapper.getSubjectBySubjectNo(subjectNo);
    }
    public void updateSubjectStatus()
    {
       List<TblSubjectDTO> subjectDTOS  =  subjectMapper.getAllSubjects();
        for (TblSubjectDTO tblSubjectDTO : subjectDTOS) {
            if(isSubjectSubjectSettlementTimestampAfterCurrentTime(tblSubjectDTO))
            {
                if (!SubjectStatus.COMPLETED.getValue().equals(tblSubjectDTO.getSubjectStatus())){
                tblSubjectDTO.setSubjectStatus(SubjectStatus.SETTLEMENT.getValue());
                subjectMapper.updateSubject(tblSubjectDTO);
                }

            }
        }
    }

    @Transactional
    public boolean BettingSettlement(long subjectNo, String result){
        if (StringConstants.YES.equals(result) || StringConstants.NO.equals(result)){
            TblSubjectDTO subjectDTO = subjectMapper.getSubjectById(subjectNo);
            boolean time = LocalDateTime.now().isAfter(subjectDTO.getSubjectSettlementTimestamp().toLocalDateTime());
            if (subjectDTO == null || !time) {
                return false;
            }
            this.setSubjectFinishResult(subjectNo,result);
            this.BetSettlement(subjectNo, subjectDTO, result);
            return true;
        }
        return false;
    }

    @Transactional
    public void BetSettlement(long subjectNo, TblSubjectDTO subjectDTO, String result) {

        //결과 상품의 정보 가져오기

        // 해당 상품 승리에 베팅한 사람들의 정보 가져오기

        Map<String,Object> map = new HashMap<>();
        map.put("subjectNo",subjectNo);
        map.put("result",result);

        List<TblBettingOrderDTO> users =  bettingOrderMapper.getUsersBySubjectNo(map);

        for (TblBettingOrderDTO user : users) {

            List<TblBettingOrderDTO> userBettingOrders = bettingOrderMapper.getBettingOrdersByUserSubjectDTO(new UserSubjectDTO(user.getOrderUserNo(),subjectNo, result));

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
        BigDecimal profitPoint=null;
        BigDecimal rate;
        if ("Yes".equals(result) && !subjectTotalYesPointBD.equals(0)) {
            rate = totalUserBettingPointBD.divide(subjectTotalYesPointBD, 2, BigDecimal.ROUND_HALF_UP);
            profitPoint = rate.multiply(subjectTotalNoPointBD);
        } else if("No".equals(result) && !subjectTotalNoPointBD.equals(0)){
            rate = totalUserBettingPointBD.divide(subjectTotalNoPointBD, 2, BigDecimal.ROUND_HALF_UP);
            profitPoint = rate.multiply(subjectTotalYesPointBD);
        }
        return profitPoint;
    }
}
