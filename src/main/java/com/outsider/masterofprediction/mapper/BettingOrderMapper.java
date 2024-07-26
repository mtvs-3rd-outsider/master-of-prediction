package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface BettingOrderMapper {

    // 구매내역 조회
    List<BettingOrderDTO> getBettingOrdersByUserId(UserPaginationDTO userPaginationDTO);

    /**
     * 특정 사용자가 한 주문의 수를 조회합니다.
     *
     * @param userId 사용자의 ID
     * @return 사용자가 한 주문의 수
     */
    int getOrderCountByUserId(Long userId);

    Long getUserTotalPointsByUserId(Long userId);

    /**
     * 특정 종목 번호에 대한 베팅 주문을 조회합니다.
     *
     * @param subjectNo 종목의 ID
     * @return 해당 종목의 베팅 주문을 포함하는 ActiveDTO 목록
     */
    List<ActiveDTO> getBettingOrdersBySubjectNo(Long subjectNo);
    List<TblBettingOrderDTO> getBettingOrdersByUserSubjectDTO(UserSubjectDTO userSubjectDTO);
    /**
     * 특정 종목 번호에 대한 랭킹을 조회합니다.
     *
     * @param subjectNo 종목의 ID
     * @return 해당 종목의 랭킹을 포함하는 RankingDTO 목록
     */
    List<RankingDTO> getRankingBySubjectNo(Long subjectNo);

    void insertBettingOrderByDTO(TblBettingOrderDTO bettingOrderDTO);

    int getYesSumPointByDTO(TblBettingOrderDTO bettingOrderDTO);

    int getNoSumPointByDTO(TblBettingOrderDTO bettingOrderDTO);

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
    List<TblBettingOrderDTO> getUsersBySubjectNo(Map<String,Object> map);
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

    List<ActivityUserSubjectDTO> findActivity();

    List<GraphDTO> getGraph1HByDTO(GraphDTO graphDTO);

    List<GraphDTO> getGraph6HByDTO(GraphDTO graphDTO);

    List<GraphDTO> getGraph1DByDTO(GraphDTO graphDTO);

    List<GraphDTO> getGraph1WByDTO(GraphDTO graphDTO);

    List<GraphDTO> getGraph1MByDTO(GraphDTO graphDTO);

    List<GraphDTO> getGraphAllByDTO(GraphDTO graphDTO);

    List<ActivityUserSubjectDTO> findActivityCount(int count);

}
