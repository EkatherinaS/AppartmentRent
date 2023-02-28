package com.rentappartment.server.model.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressDao {

    @Autowired
    private AddressRepository repository;

    public void save(Address address) {
        repository.save(address);
    }

    public void delete(Address address) {
        repository.delete(address);
    }

    public List<Address> getAllAddresses() {
        List<Address> list = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(list::add);
        return list;
    }

    public void deleteAllAddresses() {
        repository.deleteAll(Streamable.of(repository.findAll()));
    }

    public Address findById(String id) {
        return repository.findById(id).orElse(null);
    }
}
