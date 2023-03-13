package com.rentappartment.server.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactDao {

    @Autowired
    private ContactRepository repository;

    public void save(Contact contact) {
        repository.save(contact);
    }

    public void delete(Contact contact) {
        repository.delete(contact);
    }

    public List<Contact> getAllContacts() {
        List<Contact> list = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(list::add);
        return list;
    }

    public void deleteAllContacts() {
        repository.deleteAll();
    }

    public Contact findById(String id) {
         return repository.findById(id).orElse(null);
    }
}
