package com.rentappartment.server.controller;

import com.rentappartment.server.model.User.User;
import com.rentappartment.server.model.User.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public User checkUser(@RequestParam(name="user_id") int userId) {
        return userDao.findById(userId);
    }

    @GetMapping("/user/create")
    public User createUser() {
        return userDao.create();
    }
}
