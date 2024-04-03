package com.example.service;

import com.example.domain.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    int addNotice(Notice notice);

    int deleteNotice(Map<String, Object> map);

    List<Notice> querynotice(Map<String, Object> map);

    int queryisread(Map<String, Object> map);

    List<Notice> queryusernotice(Map<String, Object> map);
}
