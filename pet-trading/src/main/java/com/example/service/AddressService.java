package com.example.service;

import com.example.domain.Address;

import java.util.List;
import java.util.Map;

public interface AddressService {
    int addAddress(Address address);

    int deleteAddress(Map<String, Object> map);

    int updateAddress(Address address);

    List<Address> queryAddressbyuid(Map<String, Object> map);

    List<Address> queryAddress(Map<String, Object> map);

    int countaddress(Map<String, Object> map);
}
