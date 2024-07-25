package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.*;
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

    public List<CommentReDTO> getCommentBySubjectNo(long subjectNo) {
        List<CommentReDTO> list = commentMapper.getCommentBySubjectNo(subjectNo);

        for (CommentReDTO dto : list) {
            TblAttachmentDTO attachment = userManagementService.getAttachmentsByUserNo(dto.getUserNo());
            if (attachment != null && attachment.getAttachmentFileAddress() != null) {
                dto.setImgUrl(FileUtil.checkFileOrigin(attachment.getAttachmentFileAddress()));
            } else {
                // 필요에 따라 기본 이미지 URL을 설정하거나 아무것도 하지 않음
                dto.setImgUrl("/images/upload/logo2.png"); // 기본 이미지 URL 설정
            }

            if (dto.getReplyUserNo() != 0) {
                TblAttachmentDTO replyAttachment = userManagementService.getAttachmentsByUserNo(dto.getReplyUserNo());
                if (replyAttachment != null && replyAttachment.getAttachmentFileAddress() != null) {
                    dto.setReplyImgUrl(FileUtil.checkFileOrigin(replyAttachment.getAttachmentFileAddress()));
                } else {
                    // 필요에 따라 기본 이미지 URL을 설정하거나 아무것도 하지 않음
                    dto.setReplyImgUrl("/images/upload/logo2.png"); // 기본 이미지 URL 설정
                }
            }
        }
        return list;
    }


    public void insertComment(TblCommentDTO commentDTO) {
        commentMapper.insertComment(commentDTO);
    }


}
