package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.CommentDTO;
import com.outsider.masterofprediction.dto.CommentReDTO;
import com.outsider.masterofprediction.dto.TblCommentDTO;
import com.outsider.masterofprediction.dto.TblReplyDTO;
import com.outsider.masterofprediction.mapper.CommentMapper;
import com.outsider.masterofprediction.utils.FileUtil;
import jdk.dynalink.beans.StaticClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentMapper commentMapper;
    private final UserManagementService userManagementService;

    @Autowired
    public CommentService(CommentMapper commentMapper, UserManagementService userManagementService) {
        this.commentMapper = commentMapper;
        this.userManagementService = userManagementService;
    }

    public List<CommentReDTO> getCommentBySubjectNo(long subjectNo){
        List<CommentReDTO> list =commentMapper.getCommentBySubjectNo(subjectNo);
        for(CommentReDTO dto: list){
            dto.setImgUrl(FileUtil.checkFileOrigin(userManagementService.getAttachmentsByUserNo(dto.getUserNo()).getAttachmentFileAddress()));
            if(dto.getReplyUserNo()!=0){
                dto.setReplyImgUrl(FileUtil.checkFileOrigin(userManagementService.getAttachmentsByUserNo(dto.getReplyUserNo()).getAttachmentFileAddress()));
            }
        }
        return list;
    }

    public void insertComment(TblCommentDTO commentDTO) {
        commentMapper.insertComment(commentDTO);
    }


}
