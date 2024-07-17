package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeDeleteByIdService {

    private final NoticeMapper noticeMapper;

    @Autowired
    public NoticeDeleteByIdService(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    public void deleteByIds(List<Long> ids) {
        noticeMapper.deleteAllByIds(ids);
    }

}
