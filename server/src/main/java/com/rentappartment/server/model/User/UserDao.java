package com.rentappartment.server.model.User;

import com.rentappartment.server.model.Favorite.Favorite;
import com.rentappartment.server.model.Favorite.FavoriteRepository;
import com.rentappartment.server.model.Offer.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserDao {
    @Autowired
    private UserRepository repository;


    public void save(User user) {
        repository.save(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public void deleteAllUsers() {
        repository.deleteAll();
    }

    public User findByLogin(String login) {
        return repository.findById(login).orElse(null);
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(list::add);
        return list;
    }

    public boolean checkUser(User userToCheck) {
        User user = repository.findById(userToCheck.getLogin()).orElse(null);
        if (user != null) {
            return Objects.equals(user.getPassword(), userToCheck.getPassword());
        }
        return false;
    }
}
