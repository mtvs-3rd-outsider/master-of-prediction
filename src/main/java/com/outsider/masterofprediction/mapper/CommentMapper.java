package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.CommentDTO;
import com.outsider.masterofprediction.dto.UserPaginationDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {

    // 댓글 수 조회
    int getCommentCountByUserId(@Param("userId") Long userId);
    // 댓글목록 조회

    List<CommentDTO> getCommentsByUserId(UserPaginationDTO userPaginationDTO);


}
