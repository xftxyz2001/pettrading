package com.example.service;

import com.example.domain.Pet;

import java.util.List;
import java.util.Map;

public interface PetService {
    int addPet(Pet pet);

    int deletePet(Map<String, Object> map);

    int updatePet(Pet pet);

    Pet queryPetById(Long pid);

    List<Pet> queryPetByName(String petname);

    List<Pet> queryAllPet(Map<String, Object> map);

    int setPetbkidNULL(Long bkid);

    List<Pet> queryPetpage(Map<String, Object> map);

    Integer querypetcount(Map<String, Object> map);

    String queryMaxPrice();

    List<Map<String, Object>> queryage();
}
