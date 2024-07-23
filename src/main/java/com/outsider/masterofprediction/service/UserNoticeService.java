package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.Notice;
import com.outsider.masterofprediction.mapper.UserNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserNoticeService {

    private final UserNoticeMapper userNoticeMapper;

    @Autowired
    public UserNoticeService(UserNoticeMapper userNoticeMapper) {
        this.userNoticeMapper = userNoticeMapper;
    }

    public List<Notice> getAllList() {
        return userNoticeMapper.getAllList();
    };

    public List<Notice> searchNoticeLimit(int first, int count){
        return userNoticeMapper.noticeListLimit(first, count);
    }

    public int totalCount() {
        return userNoticeMapper.totalCount();
    }

    public Notice noticeById(Long notificationId) {
        return userNoticeMapper.getNoticeById(notificationId);
    }
}
