package com.example.service.impl;

import com.example.dao.BKindMapper;
import com.example.domain.BKind;
import com.example.service.BKindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BKindServiceImpl implements BKindService {

    private final BKindMapper BKindMapper;

    @Override
    public int addBKind(BKind bkind) {
        return BKindMapper.addBKind(bkind);
    }

    @Override
    public int deleteBKind(Long bkid) {
        return BKindMapper.deleteBKind(bkid);
    }

    @Override
    public int updateBKind(BKind bkind) {
        return BKindMapper.updateBKind(bkind);
    }

    @Override
    public List<BKind> queryBKind() {
        return BKindMapper.queryBKind();
    }

    @Override
    public List<Map<String, Object>> queryBKindname() {
        return BKindMapper.queryBKindname();
    }

    @Override
    public List<BKind> queryBKindandSKind(Map<String, Object> map) {
        return BKindMapper.queryBKindandSKind(map);
    }
}
