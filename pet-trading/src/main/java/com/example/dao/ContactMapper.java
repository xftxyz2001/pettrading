package com.example.dao;

import com.example.domain.Contact;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ContactMapper {

    @Insert("insert into contact (fromuid,touid,noread) value (#{fromuid},#{touid},#{noread})")
    @Options(useGeneratedKeys = true, keyProperty = "cid")
    int addContact(Contact contact);

    int deleteContact(Map<String, Object> map);

    int updateContact(Contact contact);

    List<Contact> queryContact(Map<String, Object> map);

    int querynoread(Map<String, Object> map);

    // List<Contact> querytouid(Map<String, Object> map);
}
