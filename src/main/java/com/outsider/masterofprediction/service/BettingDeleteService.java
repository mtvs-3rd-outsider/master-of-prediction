package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.BettingDeleteOrderDTO;
import com.outsider.masterofprediction.mapper.AttachmentMapper;
import com.outsider.masterofprediction.mapper.BettingDeleteMapper;
import com.outsider.masterofprediction.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BettingDeleteService {
    private final BettingDeleteMapper bettingDeleteMapper;
    private final SubjectMapper subjectMapper;
    private final AttachmentMapper attachmentMapper;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BettingDeleteService(SubjectMapper subjectMapper, AttachmentMapper attachmentMapper,
                                BettingDeleteMapper bettingDeleteMapper, JdbcTemplate jdbcTemplate) {
        this.subjectMapper = subjectMapper;
        this.attachmentMapper = attachmentMapper;
        this.bettingDeleteMapper = bettingDeleteMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void delete(Long subjectNo, Long attachmentNo){
        //
        this.attachmentMapper.deleteAttachmentsBySubjectNo(subjectNo);
        this.subjectMapper.deleteSubject(subjectNo);
    }

    @Transactional
    public void deleteList(List<Long> ids){
        for (Long id : ids) {
            // 구매한 사용자 불러오기 및 포인트 조회
            List<BettingDeleteOrderDTO> bettingDeleteOrderDTO =
                    this.bettingDeleteMapper.findSumOrderAmountBySubjectNo(id);

                StringBuilder changeReason = new StringBuilder("'" + id + "번상품 삭제로 인한 환불'");
                // 포인트 변경내역에 추가
                List<Object[]> pointHistoryBadtch = new ArrayList<>();
                List<Object[]> userPointBadth = new ArrayList<>();

                String insertPointHistorySQL =
                        "insert into tbl_point_history (change_amount, change_user_no, change_reason) " +
                        "values (?, ?, " + changeReason.toString() + ")";
            String insertUserPointSQL = "update tbl_user set user_point = user_point + ? where user_no = ?";

            for (BettingDeleteOrderDTO dto : bettingDeleteOrderDTO) {
                // 각 사용자별로 환불 내역을 추가합니다.
                Object[] pointHistory = new Object[] { dto.getPoint(), dto.getUserId() };
                Object[] userModify = new Object[] { dto.getPoint(), dto.getUserId() };
                pointHistoryBadtch.add(pointHistory);
                userPointBadth.add(userModify);
            }

            // 배치 업데이트를 수행합니다.
            // 회원의 보유포인트에 추가
            jdbcTemplate.batchUpdate(insertPointHistorySQL, pointHistoryBadtch);
            jdbcTemplate.batchUpdate(insertUserPointSQL, userPointBadth);
            // 상품 이미지 삭제
            this.attachmentMapper.deleteAttachmentsBySubjectNo(id);
            // 상품 삭제
            this.subjectMapper.deleteSubject(id);
        }
    }
}
