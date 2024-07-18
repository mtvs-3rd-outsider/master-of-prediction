package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.Notice;
import com.outsider.masterofprediction.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeCreateService {

    private final NoticeMapper noticeMapper;

    @Autowired
    public NoticeCreateService(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    public void creat(Notice notice){
        Long id = UserSession.getUserId();
        if (!UserSession.isAdmin()) {
            return ;
        }
        notice.setUserId(id);
        noticeMapper.create(notice);
    }
}
