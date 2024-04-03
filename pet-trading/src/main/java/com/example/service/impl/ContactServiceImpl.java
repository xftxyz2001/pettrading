package com.example.service.impl;

import com.example.dao.ContactMapper;
import com.example.domain.Contact;
import com.example.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactMapper contactMapper;

    @Override
    public int addContact(Contact contact) {
        return contactMapper.addContact(contact);
    }

    @Override
    public int deleteContact(Map<String, Object> map) {
        return contactMapper.deleteContact(map);
    }

    @Override
    public int updateContact(Contact contact) {
        return contactMapper.updateContact(contact);
    }

    @Override
    public List<Contact> queryContact(Map<String, Object> map) {
        return contactMapper.queryContact(map);
    }

    @Override
    public int querynoread(Map<String, Object> map) {
        return contactMapper.querynoread(map);
    }
}
