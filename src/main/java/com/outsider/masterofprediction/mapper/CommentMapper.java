package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.Comment;
import com.outsider.masterofprediction.dto.UserPaginationDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface CommentMapper {

    // 댓글 수 조회
    int getCommentCountByUserId(@Param("userId") Long userId);
    // 댓글목록 조회

    List<Comment> getCommentsByUserId(UserPaginationDTO userPaginationDTO);


}
