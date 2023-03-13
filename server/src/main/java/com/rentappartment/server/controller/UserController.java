package com.rentappartment.server.controller;

import com.rentappartment.server.model.Image.Image;
import com.rentappartment.server.model.Image.ImageDao;
import com.rentappartment.server.model.Offer.Offer;
import com.rentappartment.server.model.User.User;
import com.rentappartment.server.model.User.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/user/get-all")
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @GetMapping("/user/check")
    public boolean checkUser(@RequestParam(name="user") User user) {
        return userDao.checkUser(user);
    }

    @GetMapping("/user/add")
    public void addUser(@RequestParam(name="user") User user) {
        userDao.save(user);
    }
}
