package com.example.dao;

import com.example.domain.Petorder;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PetorderMapper {

    @Insert("insert into petorder (date,pid,uid,address,phone,recipientname,postatu,puid,price,newphone,newaddress,newrecipientname) value (#{date},#{pid},#{uid},#{address},#{phone},#{recipientname},#{postatu},#{puid},#{price},#{newphone},#{newaddress},#{newrecipientname})")
    int addpetorder(Petorder petorder);

    int deletepetorder(Map<String, Object> map);

    int updatepetorder(Petorder petorder);

    List<Petorder> querypetorder(Map<String, Object> map);

    List<Petorder> querypetorderpage(Map<String, Object> map);

    int countpetorder(Map<String, Object> map);
}
