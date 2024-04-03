package com.example.service;

import com.example.domain.Contact;

import java.util.List;
import java.util.Map;

public interface ContactService {
    int addContact(Contact contact);

    int deleteContact(Map<String, Object> map);

    int updateContact(Contact contact);


    List<Contact> queryContact(Map<String, Object> map);

    int querynoread(Map<String, Object> map);
}
