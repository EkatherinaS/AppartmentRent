package com.rentappartment.server.controller;

import com.rentappartment.server.model.Contact.Contact;
import com.rentappartment.server.model.Contact.ContactDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private ContactDao contactDao;

    @GetMapping("/contact/get-all")
    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }
}
