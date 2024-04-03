package com.example.dao;

import com.example.domain.Address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AddressMapper {

    @Insert("insert into address (phone,address,uid,username) value (#{phone},#{address},#{uid},#{username})")
    @Options(useGeneratedKeys = true, keyProperty = "aid")
    int addAddress(Address address);

    int deleteAddress(Map<String, Object> map);

    int updateAddress(Address address);

    @Select("select * from address where uid=#{uid}")
    List<Address> queryAddressbyuid(Map<String, Object> map);

    List<Address> queryAddress(Map<String, Object> map);

    int countaddress(Map<String, Object> map);
}
