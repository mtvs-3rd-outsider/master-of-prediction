package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BettingOrderMapper {

    /**
     * 페이지네이션 세부 정보를 기반으로 사용자의 구매 내역을 조회합니다.
     *
     * @param userPaginationDTO 베팅 주문을 조회하기 위한 페이지네이션 세부 정보
     * @return 사용자의 베팅 주문을 포함하는 BettingOrderDTO 목록
     */
    List<BettingOrderDTO> getBettingOrdersByUserId(UserPaginationDTO userPaginationDTO);

    /**
     * 특정 사용자가 한 주문의 수를 조회합니다.
     *
     * @param userId 사용자의 ID
     * @return 사용자가 한 주문의 수
     */
    int getOrderCountByUserId(Long userId);

    /**
     * 특정 사용자의 총 포인트를 조회합니다.
     *
     * @param userId 사용자의 ID
     * @return 사용자의 총 포인트
     */
    Long getUserTotalPointsByUserId(Long userId);

    /**
     * 특정 종목 번호에 대한 베팅 주문을 조회합니다.
     *
     * @param subjectNo 종목의 ID
     * @return 해당 종목의 베팅 주문을 포함하는 ActiveDTO 목록
     */
    List<ActiveDTO> getBettingOrdersBySubjectNo(Long subjectNo);

    /**
     * 특정 종목 번호에 대한 랭킹을 조회합니다.
     *
     * @param subjectNo 종목의 ID
     * @return 해당 종목의 랭킹을 포함하는 RankingDTO 목록
     */
    List<RankingDTO> getRankingBySubjectNo(Long subjectNo);

    /**
     * 특정 사용자의 이번 달 총 포인트를 조회합니다.
     *
     * @param userId 사용자의 ID
     * @return 사용자의 이번 달 총 포인트
     */
    Long getMonthTotalPointsByUser(Long userId);

    /**
     * 특정 사용자의 이번 달 총 수익 포인트를 조회합니다.
     *
     * @param userId 사용자의 ID
     * @return 사용자의 이번 달 총 수익 포인트
     */
    Long getMonthTotalProfitsByUserId(Long userId);

    /**
     * 특정 사용자의 현재 포지션 가치 총합을 조회합니다.
     *
     * @param userId 사용자의 ID
     * @return 사용자의 현재 포지션 가치 총합
     */
    Long getTotalPositionValueByUserId(Long userId);

    /**
     * 특정 사용자의 현재 포지션 가치를 조회합니다.
     *
     * @param userId 사용자의 ID
     * @return 사용자의 현재 포지션 가치를 포함하는 BettingInfoDTO 목록
     */
    List<BettingInfoDTO> getPositionValueByUserId(Long userId);

    /**
     * 특정 사용자의 한 달 동안의 수익 포인트와 손실 포인트를 조회합니다.
     *
     * @param userId 사용자의 ID
     * @return 사용자의 한 달 동안의 수익 포인트와 손실 포인트를 포함하는 BettingInfoDTO 목록
     */
    List<BettingInfoDTO> getMonthProfitsByUserId(Long userId);

}
