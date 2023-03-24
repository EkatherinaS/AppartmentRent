package com.rentappartment.server.controller;

import com.rentappartment.server.model.Address.Address;
import com.rentappartment.server.model.Address.AddressDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {
    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
    @Autowired
    public AddressDao addressDao;


    @GetMapping("/address/get-all")
    public List<Address> getAllAddress() {
        List<Address> list = addressDao.getAllAddresses();
        logger.info("getAllAddress finished, objects found: " + list.size());
        return list;
    }
}
