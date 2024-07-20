package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.CommentReDTO;
import com.outsider.masterofprediction.dto.TblCommentDTO;
import com.outsider.masterofprediction.dto.TblReplyDTO;
import com.outsider.masterofprediction.mapper.CommentMapper;
import jdk.dynalink.beans.StaticClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public List<CommentReDTO> getCommentBySubjectNo(long subjectNo){
        return commentMapper.getCommentBySubjectNo(subjectNo);
    }

    public void insertComment(TblCommentDTO commentDTO) {
        commentMapper.insertComment(commentDTO);
    }


}
