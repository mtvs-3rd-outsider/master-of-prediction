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
public class BuyItemService {
    private final UserMapper userMapper;
    private final BettingOrderMapper bettingOrderMapper;
    private final SubjectMapper subjectMapper;
    private final PointHistoryMapper pointHistoryMapper;
    private final SseEmitters sseEmitters;

    @Autowired
    public BuyItemService(UserMapper userMapper, BettingOrderMapper bettingOrderMapper, SubjectMapper subjectMapper, PointHistoryMapper pointHistoryMapper, SseEmitters sseEmitters) {
        this.userMapper = userMapper;
        this.bettingOrderMapper = bettingOrderMapper;
        this.subjectMapper = subjectMapper;
        this.pointHistoryMapper = pointHistoryMapper;
        this.sseEmitters = sseEmitters;
    }

    @Transactional
    public void buyItemByDTO(TblBettingOrderDTO bettingOrderDTO) throws BuyItemFailedException {
        if(bettingOrderDTO.getOrderChoice().equals("YES")){
            bettingOrderDTO.setOrderChoice("Yes");
        }else if(bettingOrderDTO.getOrderChoice().equals("NO")){
            bettingOrderDTO.setOrderChoice("No");
        }
        int updatedRows = userMapper.updateUserPointByDTO(bettingOrderDTO);
        if (updatedRows == 0) {
            throw new BuyItemFailedException("포인트가 부족합니다.");
        }
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
