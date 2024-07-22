package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.TblReplyDTO;
import com.outsider.masterofprediction.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
    private final ReplyMapper replyMapper;

    @Autowired
    public ReplyService(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }


    public void insertReply(TblReplyDTO replyDTO) {
        replyMapper.insertReply(replyDTO);
    }
}
