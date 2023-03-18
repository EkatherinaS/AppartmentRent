package com.rentappartment.server.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDao {
    @Autowired
    private UserRepository repository;

    public void delete(User user) {
        repository.delete(user);
    }
    public User create() {
        return repository.save(new User());
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(list::add);
        return list;
    }

    public void deleteAllUsers() {
        repository.deleteAll();
    }

    public User findById(int userId) {
        return repository.findById(userId).orElse(null);
    }
}
