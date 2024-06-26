package com.example.service;

import com.example.domain.Petorder;

import java.util.List;
import java.util.Map;

public interface PetorderService {
    int addpetorder(Petorder petorder);

    int deletepetorder(Map<String, Object> map);

    int updatepetorder(Petorder petorder);

    List<Petorder> querypetorder(Map<String, Object> map);

    List<Petorder> querypetorderpage(Map<String, Object> map);

    int countpetorder(Map<String, Object> map);
}
