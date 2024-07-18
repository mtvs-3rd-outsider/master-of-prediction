package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.Notice;
import com.outsider.masterofprediction.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeUpdateService {


    private final NoticeMapper noticeMapper;


    @Autowired
    public NoticeUpdateService(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    public int noticeDetailUpdate(Notice notice){
        return noticeMapper.updateDetail(notice);
    }

}
