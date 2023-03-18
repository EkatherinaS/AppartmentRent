package com.rentappartment.server.model.Favorite;

import com.rentappartment.server.model.Offer.Offer;
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

    public Favorite save(Favorite favorite) {
        return repository.save(favorite);
    }

    public Favorite delete(Favorite favorite) {
        repository.delete(favorite);
        return favorite;
    }

    public void deleteAllFavorites() {
        repository.deleteAll();
    }

    public Favorite findById(FavoriteId id) {
        return repository.findById(id).orElse(null);
    }

    public List<Favorite> getAllFavorite() {
        List<Favorite> list = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(list::add);
        return list;
    }

    public List<Offer> getFavoriteOffersByUser(int userId) {
        List<Offer> list = new ArrayList<>();
        Streamable.of(repository.findAll())
                .filter(favorite -> favorite.getUser().getUserId() == userId)
                .forEach(favorite -> list.add(favorite.getOffer()));
        return list;
    }
}
