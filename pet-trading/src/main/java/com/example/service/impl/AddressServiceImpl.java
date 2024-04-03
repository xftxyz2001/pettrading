package com.example.service.impl;

import com.example.dao.AddressMapper;
import com.example.domain.Address;
import com.example.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressMapper addressMapper;

    @Override
    public int addAddress(Address address) {
        return addressMapper.addAddress(address);
    }

    @Override
    public int deleteAddress(Map<String, Object> map) {
        return addressMapper.deleteAddress(map);
    }

    @Override
    public int updateAddress(Address address) {
        return addressMapper.updateAddress(address);
    }

    @Override
    public List<Address> queryAddressbyuid(Map<String, Object> map) {
        return addressMapper.queryAddressbyuid(map);
    }

    @Override
    public List<Address> queryAddress(Map<String, Object> map) {
        return addressMapper.queryAddress(map);
    }

    @Override
    public int countaddress(Map<String, Object> map) {
        return addressMapper.countaddress(map);
    }
}
