package com.rentappartment.server.controller;

import com.rentappartment.server.model.Address.Address;
import com.rentappartment.server.model.Address.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressDao addressDao;

    @GetMapping("/address/get-all")
    public List<Address> getAllAddress() {
        return addressDao.getAllAddresses();
    }

    /*@PostMapping("/address/save")
    public void saveNewAddress(@RequestBody Address address) {
        addressDao.save(address);
    }*/
}
