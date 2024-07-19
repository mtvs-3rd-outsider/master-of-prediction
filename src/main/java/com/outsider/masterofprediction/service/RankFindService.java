package com.outsider.masterofprediction.service;


import com.outsider.masterofprediction.dto.TblAttachmentDTO;
import com.outsider.masterofprediction.dto.TblUserDTO;
import com.outsider.masterofprediction.dto.User;
import com.outsider.masterofprediction.mapper.AttachmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;

@Service
public class RankFindService {

    private AttachmentMapper attachmentMapper;

    @Autowired
    public RankFindService(AttachmentMapper attachmentMapper){
        this.attachmentMapper = attachmentMapper;
    }

    public List<TblAttachmentDTO> findRank(){
        return attachmentMapper.findRank();
    }
}
