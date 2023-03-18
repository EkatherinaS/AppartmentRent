package com.rentappartment.server.controller;

import com.rentappartment.server.model.Favorite.Favorite;
import com.rentappartment.server.model.Favorite.FavoriteDao;
import com.rentappartment.server.model.Image.Image;
import com.rentappartment.server.model.Offer.Offer;
import com.rentappartment.server.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FavoriteController {
    @Autowired
    private FavoriteDao favoriteDao;

    @GetMapping("/favorite/get-all")
    public List<Favorite> getAllFavorite() {
        return favoriteDao.getAllFavorite();
    }

    @GetMapping("/favorite/get-by-user")
    public List<Offer> getFavoriteOffers(@RequestParam(name="user_id") int userId) {
        return favoriteDao.getFavoriteOffersByUser(userId);
    }

    @PostMapping("/favorite/save")
    public Favorite saveFavorite(@RequestBody Favorite favorite) {
        return favoriteDao.save(favorite);
    }

    @PostMapping("/favorite/delete")
    public Favorite deleteFavorite(@RequestBody Favorite favorite) {
        return favoriteDao.delete(favorite);
    }
}
