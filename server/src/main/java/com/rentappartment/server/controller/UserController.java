package com.rentappartment.server.controller;

import com.rentappartment.server.model.Image.Image;
import com.rentappartment.server.model.User.User;
import com.rentappartment.server.model.User.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserDao userDao;

    @GetMapping("/user/get-all")
    public List<User> getAllUsers() {
        List<User> list = userDao.getAllUsers();
        logger.info("getAllUsers finished, objects found: " + list.size());
        return list;
    }

    @GetMapping("/user/check")
    public User checkUser(@RequestParam(name="user_id") int userId) {
        User user = userDao.findById(userId);
        logger.info("getAllUsers finished, object found: " + user + " userId: " + userId);
        return user;
    }

    @GetMapping("/user/create")
    public User createUser() {
        User user = userDao.create();
        logger.info("createUser finished, created user with id: " + user.getUserId());
        return user;
    }
}
