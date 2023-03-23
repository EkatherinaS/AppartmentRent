package com.rentappartment.server.controller;

import com.rentappartment.server.model.Contact.Contact;
import com.rentappartment.server.model.Favorite.Favorite;
import com.rentappartment.server.model.Favorite.FavoriteDao;
import com.rentappartment.server.model.Image.Image;
import com.rentappartment.server.model.Offer.Offer;
import com.rentappartment.server.model.User.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FavoriteController {
    private static final Logger logger = LoggerFactory.getLogger(FavoriteController.class);

    @Autowired
    private FavoriteDao favoriteDao;

    @GetMapping("/favorite/get-all")
    public List<Favorite> getAllFavorite() {
        List<Favorite> list = favoriteDao.getAllFavorite();
        logger.info("getAllFavorite finished, objects found: " + list.size());
        return list;
    }

    @GetMapping("/favorite/get-by-user")
    public List<Offer> getFavoriteOffers(@RequestParam(name="user_id") int userId) {
        List<Offer> list = favoriteDao.getFavoriteOffersByUser(userId);
        logger.info("getFavoriteOffers finished, objects found: " + list.size() + " userId: " + userId);
        return list;
    }

    @PostMapping("/favorite/save")
    public Favorite saveFavorite(@RequestBody Favorite favorite) {
        Favorite object = favoriteDao.save(favorite);
        logger.info("saveFavorite finished, object saved: " + object);
        return object;
    }

    @PostMapping("/favorite/delete")
    public Favorite deleteFavorite(@RequestBody Favorite favorite) {
        Favorite object = favoriteDao.delete(favorite);
        logger.info("deleteFavorite finished, object deleted: " + object);
        return object;
    }
}
