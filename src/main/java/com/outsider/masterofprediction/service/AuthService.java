package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.EmailAuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public EmailAuthDto getEmailAuth(String email) {
        ValueOperations<String, Object> valOperations = redisTemplate.opsForValue();
        return (EmailAuthDto) valOperations.get(email);
    }
}