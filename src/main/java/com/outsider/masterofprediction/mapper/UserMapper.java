package com.outsider.masterofprediction.mapper;


import com.outsider.masterofprediction.dto.TblAttachmentDTO;
import com.outsider.masterofprediction.dto.TblBettingOrderDTO;
import com.outsider.masterofprediction.dto.TblUserDTO;
import com.outsider.masterofprediction.dto.User;
import com.outsider.masterofprediction.dto.UserAttachmentDTO;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface UserMapper {

    @Results(id = "userResultMap", value = {
            @Result(property = "id", column = "user_no"),
            @Result(property = "name", column = "user_name"),
            @Result(property = "email", column = "user_email"),
            @Result(property = "password", column = "user_password"),
            @Result(property = "joinDate", column = "user_join_date"),
            @Result(property = "authority", column = "user_authority"),
            @Result(property = "point", column = "user_point"),
            @Result(property = "imgURL", column = "user_image_url"),
            @Result(property = "withdrawalStatus", column = "user_withdrawal_status"),
    })
    @Select("SELECT * FROM tbl_user")
    List<User> findAll();

    @Select("SELECT user_no, user_point, tier_no FROM tbl_user ORDER BY user_point ")
    List<TblUserDTO> findAllReturnTblUserDTO();

    //id로유저 조회
    @Select("SELECT * FROM tbl_user WHERE user_no=#{id}")
    User getUserById(Long id);

    @Select("SELECT * FROM tbl_user WHERE user_no > #{id}")
    @ResultMap("userResultMap")
    List<User> findByNo(Long id);

    @Select("SELECT * FROM tbl_user WHERE user_email = #{email}")
    @ResultMap("userResultMap")
    User findByEmail(String email);

    @Insert("insert into tbl_user(" +
            "user_name, " +
            "user_email, " +
            "user_password, " +
            "user_authority" +
            ") VALUES  (#{name}, #{email}, #{password}, #{authority})")
    @ResultMap("userResultMap")
    void createUser(String name, String email, String password, String authority);

    @Update("UPDATE tbl_user SET " +
            "user_name = #{name}, " +
            "user_password = #{password}, " +
            "user_authority = #{authority} " +
            "WHERE user_email = #{email}")
    @ResultMap("userResultMap")
    void updateUser(User user);

    @Delete("DELETE FROM tbl_user WHERE user_email = #{email}")
    void deleteUser(String email);
        //id로 유저 포인트 업데이트
    @Update("UPDATE tbl_user SET " +
            "user_point=#{point} " +
            "WHERE user_no=#{id}"
    )
    void updateUserPointById(Long id, BigDecimal point);

    @Select("SELECT SUM(user_point) FROM tbl_user")
    BigDecimal findAllUsrPointSUM();

    @Update("UPDATE tbl_user SET tier_no = #{tierNo} WHERE user_point >= #{beginPoint} AND user_point <= #{endPoint}")
    void updateTier(Long tierNo, BigDecimal beginPoint, BigDecimal endPoint);

    @Update({
            "<script>",
            "UPDATE tbl_user SET tier_no = CASE ",
            "<foreach collection='users' item='user' separator=' '>",
            "WHEN user_no = #{user.userNo} THEN #{user.tierNo} ",
            "</foreach>",
            "END ",
            "WHERE user_no IN ",
            "<foreach collection='users' item='user' open='(' separator=',' close=')'>",
            "#{user.userNo}",
            "</foreach>",
            "</script>"
    })
    void bulkUpdateUserTiers(@Param("users") List<TblUserDTO> users);

    // 회원 탈퇴 여부 수정
    void updateWithdrawalStatusByUser(User user);

    Double getUserProfitLossRate(Long userNo);

    String getAuthorityBySubjectNo(long subjectUserNo);

    int updateUserPointByDTO(TblBettingOrderDTO bettingOrderDTO);

    void updateUserPointBySum(TblBettingOrderDTO bettingOrderDTO);

    List<UserAttachmentDTO> findAllRank();

    Long getSumYPointByDTO(TblBettingOrderDTO dto);
    Long getSumNPointByDTO(TblBettingOrderDTO dto);
}
