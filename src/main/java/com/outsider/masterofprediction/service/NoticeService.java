package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.Notice;
import com.outsider.masterofprediction.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    private final NoticeMapper noticeMapper;

    @Autowired
    public NoticeService(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    public List<Notice> noticeAll(){
        return noticeMapper.selectAll();
    }

    public List<Notice> noticeList(){
        return noticeMapper.noticeList();
    }

    public Notice noticeById(Long id){
        return noticeMapper.selectById(id);
    }

    public int updateNotice(Notice notice){
        return noticeMapper.updateDetail(notice);
    }

    public List<Notice> searchNoticeLimit(int first, int count){
        return noticeMapper.noticeListLimit(first, count);
    }


    public int totalCount(){
        return noticeMapper.totalCount();
    }
}
