package com.outsider.masterofprediction.service;


import com.outsider.masterofprediction.dto.TblUserDTO;
import com.outsider.masterofprediction.dto.User;
import com.outsider.masterofprediction.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankFindService {

    private UserMapper userMapper;

    @Autowired
    public RankFindService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public List<User> findRank(){
        return userMapper.findRank();
    }
}
