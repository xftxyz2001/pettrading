package com.example.service.impl;

import com.example.dao.NoticeMapper;
import com.example.domain.Notice;
import com.example.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeMapper noticeMapper;

    @Override
    public int addNotice(Notice notice) {
        return noticeMapper.addNotice(notice);
    }

    @Override
    public int deleteNotice(Map<String, Object> map) {
        return noticeMapper.deleteNotice(map);
    }

    @Override
    public List<Notice> querynotice(Map<String, Object> map) {
        return noticeMapper.querynotice(map);
    }

    @Override
    public int queryisread(Map<String, Object> map) {
        return noticeMapper.queryisread(map);
    }

    @Override
    public List<Notice> queryusernotice(Map<String, Object> map) {
        return noticeMapper.queryusernotice(map);
    }
}
