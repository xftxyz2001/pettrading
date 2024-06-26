package com.example.service;

import com.example.domain.SKind;

import java.util.List;
import java.util.Map;

public interface SKindService {
    int addSKind(SKind skind);

    int deleteSKind(Map<String, Object> map);

    int updateSKind(SKind skind);

    List<SKind> queryAllSKind(Map<String, Object> map);
}
