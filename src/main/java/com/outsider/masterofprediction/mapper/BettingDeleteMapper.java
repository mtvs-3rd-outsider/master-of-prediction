package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.BettingDeleteOrderDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BettingDeleteMapper {

    @Select("SELECT order_user_no, sum(order_amount) " +
            "FROM tbl_betting_order " +
            "WHERE order_subject_no = #{id} " +
            "GROUP BY order_user_no")
    List<BettingDeleteOrderDTO> findSumOrderAmountBySubjectNo(Long id);

    @Insert("INSERT INTO tbl_point_history (change_amount, change_reason, change_user_no) " +
            "VALUES (#{userId}, #{point}, '상품 삭제로 인한 환불')")
    void insertPointHistory(BettingDeleteOrderDTO bettingDeleteOrderDTO);
}
