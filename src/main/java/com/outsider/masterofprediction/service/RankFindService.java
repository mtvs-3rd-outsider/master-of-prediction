package com.outsider.masterofprediction.service;


import com.outsider.masterofprediction.dto.TblAttachmentDTO;
import com.outsider.masterofprediction.dto.TblUserDTO;
import com.outsider.masterofprediction.dto.User;
import com.outsider.masterofprediction.dto.UserAttachmentDTO;
import com.outsider.masterofprediction.mapper.AttachmentMapper;
import com.outsider.masterofprediction.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;

@Service
public class RankFindService {

    private UserMapper userMapper;

    @Autowired
    public RankFindService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public List<UserAttachmentDTO> findAllRank(){
        return userMapper.findAllRank();
    }
}
