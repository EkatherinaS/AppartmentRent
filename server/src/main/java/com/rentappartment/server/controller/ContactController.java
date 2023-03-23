package com.rentappartment.server.controller;

import com.rentappartment.server.model.Contact.Contact;
import com.rentappartment.server.model.Contact.ContactDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactDao contactDao;

    @GetMapping("/contact/get-all")
    public List<Contact> getAllContacts() {
        List<Contact> list = contactDao.getAllContacts();
        logger.info("getAllContacts finished, objects found: " + list.size());
        return list;
    }
}
