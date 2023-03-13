package com.rentappartment.server.controller;

import com.rentappartment.server.model.Favorite.Favorite;
import com.rentappartment.server.model.Favorite.FavoriteDao;
import com.rentappartment.server.model.Image.Image;
import com.rentappartment.server.model.Offer.Offer;
import com.rentappartment.server.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Offer> getFavoriteOffers(@RequestParam(name="user") User user) {
        return favoriteDao.getFavoriteOffersByUser(user);
    }

    @GetMapping("/favorite/add")
    public void addFavorite(@RequestParam(name="user") User user,
                        @RequestParam(name="offer") Offer offer) {
        Favorite favorite = new Favorite();
        favorite.setOffer(offer);
        favorite.setUser(user);
        favoriteDao.save(favorite);
    }
}
