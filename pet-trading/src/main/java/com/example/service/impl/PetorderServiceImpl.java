package com.example.service.impl;

import com.example.dao.PetorderMapper;
import com.example.domain.Petorder;
import com.example.service.PetorderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PetorderServiceImpl implements PetorderService {

    private final PetorderMapper petorderMapper;

    @Override
    public int addpetorder(Petorder petorder) {
        return petorderMapper.addpetorder(petorder);
    }

    @Override
    public int deletepetorder(Map<String, Object> map) {
        return petorderMapper.deletepetorder(map);
    }

    @Override
    public int updatepetorder(Petorder petorder) {
        return petorderMapper.updatepetorder(petorder);
    }

    @Override
    public List<Petorder> querypetorder(Map<String, Object> map) {
        return petorderMapper.querypetorder(map);
    }

    @Override
    public List<Petorder> querypetorderpage(Map<String, Object> map) {
        return petorderMapper.querypetorderpage(map);
    }

    @Override
    public int countpetorder(Map<String, Object> map) {
        return petorderMapper.countpetorder(map);
    }
}
