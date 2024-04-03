package com.example.service.impl;

import com.example.dao.SKindMapper;
import com.example.domain.SKind;
import com.example.service.SKindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class SKindServiceImpl implements SKindService {

    private final SKindMapper sKindMapper;

    @Override
    public int addSKind(SKind skind) {
        return sKindMapper.addSKind(skind);
    }

    @Override
    public int deleteSKind(Map<String, Object> map) {
        return sKindMapper.deleteSKind(map);
    }

    @Override
    public int updateSKind(SKind skind) {
        return sKindMapper.updateSKind(skind);
    }

    @Override
    public List<SKind> queryAllSKind(Map<String, Object> map) {
        return sKindMapper.queryAllSKind(map);
    }
}
