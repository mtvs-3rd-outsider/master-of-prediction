package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.CustomUserDetail;
import com.outsider.masterofprediction.dto.User;
import com.outsider.masterofprediction.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// security 파일
@Service
public class CustomUserService implements UserDetailsService {

    private final UserMapper userMapper;

    @Autowired
    public CustomUserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userMapper.findByEmail(userEmail);
        CheckWithdrawalStatus(user);
        if (user != null){
            return new CustomUserDetail(user);
        }
        return null;
    }

    public void CheckWithdrawalStatus(User user) {
        if (user == null){
            return;
        }
        if(user.getWithdrawalStatus())
        {
            user.setWithdrawalStatus(false);
            userMapper.updateWithdrawalStatusByUser(user);
        }
    }
}
