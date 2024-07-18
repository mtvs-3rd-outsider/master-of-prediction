package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.BettingOrderDTO;
import com.outsider.masterofprediction.dto.User;
import com.outsider.masterofprediction.dto.UserPaginationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface BettingOrderMapper {

    // 구매내역 조회
    List<BettingOrderDTO> getBettingOrdersByUserId(UserPaginationDTO userPaginationDTO);
    // 종목 수 조회
    int getOrderCountByUserId(Long userId);

    Long getUserTotalPointsByUserId(Long userId);
}
