package com.rentappartment.server.model.Favorite;

import com.rentappartment.server.model.Offer.Offer;
import com.rentappartment.server.model.Offer.OfferRepository;
import com.rentappartment.server.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteDao {
    @Autowired
    private FavoriteRepository repository;

    public void save(Favorite favorite) {
        repository.save(favorite);
    }

    public void delete(Favorite favorite) {
        repository.delete(favorite);
    }

    public void deleteAllFavorites() {
        repository.deleteAll();
    }

    public Favorite findById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Favorite> getAllFavorite() {
        List<Favorite> list = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(list::add);
        return list;
    }

    public List<Offer> getFavoriteOffersByUser(User user) {
        List<Offer> list = new ArrayList<>();
        Streamable.of(repository.findAll())
                .filter(favorite -> favorite.getUser() == user)
                .forEach(favorite -> list.add(favorite.getOffer()));
        return list;
    }
}
