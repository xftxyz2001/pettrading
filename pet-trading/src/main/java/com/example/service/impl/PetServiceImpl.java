package com.example.service.impl;

import com.example.dao.PetMapper;
import com.example.domain.Pet;
import com.example.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {

    private final PetMapper petMapper;

    @Override
    public int addPet(Pet pet) {
        return petMapper.addPet(pet);
    }

    @Override
    public int deletePet(Map<String, Object> map) {
        return petMapper.deletePet(map);
    }

    @Override
    public int updatePet(Pet pet) {
        return petMapper.updatePet(pet);
    }

    @Override
    public Pet queryPetById(Long pid) {
        return petMapper.queryPetById(pid);
    }

    @Override
    public List<Pet> queryPetByName(String petname) {
        return petMapper.queryPetByName(petname);
    }

    @Override
    public List<Pet> queryAllPet(Map<String, Object> map) {
        return petMapper.queryAllPet(map);
    }

    @Override
    public int setPetbkidNULL(Long bkid) {
        return petMapper.setPetbkidNULL(bkid);
    }

    @Override
    public List<Pet> queryPetpage(Map<String, Object> map) {
        return petMapper.queryPetpage(map);
    }

    @Override
    public Integer querypetcount(Map<String, Object> map) {
        return petMapper.querypetcount(map);
    }

    @Override
    public String queryMaxPrice() {
        return petMapper.queryMaxPrice();
    }

    @Override
    public List<Map<String, Object>> queryage() {
        return petMapper.queryage();
    }
}
