package com.example.controller;

import com.example.domain.Contact;
import com.example.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/updatecontact")
    public String updatecontact(Contact contact) {
        contactService.updateContact(contact);
        return "更改成功";
    }

    @PostMapping("/addContact")
    public Long addContact(Contact contact) {
        Map<String, Object> map = new HashMap<>();
        map.put("fromuid", contact.getFromuid());
        map.put("touid", contact.getTouid());
        List<Contact> contacts = contactService.queryContact(map);
        if (contacts.isEmpty()) {
            contactService.addContact(contact);
            return contact.getCid();
        } else {
            return contacts.get(0).getCid();
        }
    }

}
