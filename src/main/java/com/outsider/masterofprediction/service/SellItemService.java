package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.ActivityUserSubjectDTO;
import com.outsider.masterofprediction.dto.TblBettingOrderDTO;
import com.outsider.masterofprediction.mapper.BettingOrderMapper;
import com.outsider.masterofprediction.mapper.PointHistoryMapper;
import com.outsider.masterofprediction.mapper.SubjectMapper;
import com.outsider.masterofprediction.mapper.UserMapper;
import com.outsider.masterofprediction.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SellItemService {
    private final BettingOrderMapper bettingOrderMapper;
    private final UserMapper userMapper;
    private final SubjectMapper subjectMapper;
    private final PointHistoryMapper pointHistoryMapper;
    private final SseEmitters sseEmitters;

    @Autowired
    public SellItemService(BettingOrderMapper bettingOrderMapper, UserMapper userMapper, SubjectMapper subjectMapper, PointHistoryMapper pointHistoryMapper, SseEmitters sseEmitters) {
        this.bettingOrderMapper = bettingOrderMapper;
        this.userMapper = userMapper;
        this.subjectMapper = subjectMapper;
        this.pointHistoryMapper = pointHistoryMapper;
        this.sseEmitters = sseEmitters;
    }

    @Transactional
    public void sellItemByDTO(TblBettingOrderDTO bettingOrderDTO) {
        int sum;

        if("YES".equals(bettingOrderDTO.getOrderChoice()) || "NO".equals(bettingOrderDTO.getOrderChoice())){
            String status = Character.toUpperCase(bettingOrderDTO.getOrderChoice()
                    .toLowerCase().charAt(0))
                    + bettingOrderDTO.getOrderChoice().toLowerCase().substring(1);
            bettingOrderDTO.setOrderChoice(status);
            // 해당 게임의 로그인 중인 아이디의 Yes구매를 활동내역으로 부터 가져와 합계를 얻습니다.
            sum=bettingOrderMapper.getYesSumPointByDTO(bettingOrderDTO);

            if (bettingOrderDTO.getOrderAmount() > sum) {
                throw new IllegalStateException("보유한 " + status +"포인트가 부족합니다.");
            }
            userMapper.updateUserPointBySum(bettingOrderDTO);
            bettingOrderDTO.setOrderAmount(-bettingOrderDTO.getOrderAmount());
            bettingOrderMapper.insertBettingOrderByDTO(bettingOrderDTO);
            subjectMapper.updateTotalPointByDTO(bettingOrderDTO);
            pointHistoryMapper.insertHistoryByDTO(bettingOrderDTO);


            // NOTE: 이벤트를 보내는 코드입니다.
            ActivityUserSubjectDTO sendData = bettingOrderMapper.findActivityByOrderNo(bettingOrderDTO.getOrderNo());
            sendData.getAttachmentUser().setAttachmentFileAddress(FileUtil.checkFileOrigin(sendData.getAttachmentUser().getAttachmentFileAddress()));
            sendData.getAttachmentSubject().setAttachmentFileAddress(FileUtil.checkFileOrigin(sendData.getAttachmentSubject().getAttachmentFileAddress()));
            sseEmitters.sendActivity(sendData);

        }
    }
}


// if(bettingOrderDTO.getOrderChoice().equals("YES")){
//     bettingOrderDTO.setOrderChoice("Yes");
//     //해당 게임의 로그인 중인 아이디의 Yes구매를 활동내역으로 부터 가져와 합계를 얻습니다.
//     sum=bettingOrderMapper.getYesSumPointByDTO(bettingOrderDTO);
//     if(bettingOrderDTO.getOrderAmount()<=sum){
//         userMapper.updateUserPointBySum(bettingOrderDTO);
//     }else{
//         //예외 발생 "보유한 YES가 부족합니다"
//         throw new IllegalStateException("보유한 YES포인트가 부족합니다.");
//     }
//     bettingOrderDTO.setOrderAmount(-bettingOrderDTO.getOrderAmount());
//     bettingOrderMapper.insertBettingOrderByDTO(bettingOrderDTO);
//     subjectMapper.updateTotalPointByDTO(bettingOrderDTO);
//     pointHistoryMapper.insertHistoryByDTO(bettingOrderDTO);
//
//
//     // NOTE: 이벤트를 보내는 코드입니다.
//     ActivityUserSubjectDTO sendData = bettingOrderMapper.findActivityByOrderNo(bettingOrderDTO.getOrderNo());
//     sendData.getAttachmentUser().setAttachmentFileAddress(FileUtil.checkFileOrigin(sendData.getAttachmentUser().getAttachmentFileAddress()));
//     sendData.getAttachmentSubject().setAttachmentFileAddress(FileUtil.checkFileOrigin(sendData.getAttachmentSubject().getAttachmentFileAddress()));
//     sseEmitters.sendActivity(sendData);
//
// }else if(bettingOrderDTO.getOrderChoice().equals("NO")){
//     bettingOrderDTO.setOrderChoice("No");
//     //해당 게임의 로그인 중인 아이디의 Yes구매를 활동내역으로 부터 가져와 합계를 얻습니다.
//     sum=bettingOrderMapper.getNoSumPointByDTO(bettingOrderDTO);
//     if(bettingOrderDTO.getOrderAmount()<=sum){
//         userMapper.updateUserPointBySum(bettingOrderDTO);
//     }else{
//         //예외 발생 "보유한 NO가 부족합니다"
//         throw new IllegalStateException("보유한 NO포인트가 부족합니다.");
//     }
//     bettingOrderDTO.setOrderAmount(-bettingOrderDTO.getOrderAmount());
//     bettingOrderMapper.insertBettingOrderByDTO(bettingOrderDTO);
//     subjectMapper.updateTotalPointByDTO(bettingOrderDTO);
//     pointHistoryMapper.insertHistoryByDTO(bettingOrderDTO);
// }