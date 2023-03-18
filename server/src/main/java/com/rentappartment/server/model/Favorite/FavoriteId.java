package com.rentappartment.server.model.Favorite;

import com.rentappartment.server.model.Offer.Offer;
import com.rentappartment.server.model.User.User;

import java.io.Serializable;

public class FavoriteId implements Serializable {
    private User user;

    private Offer offer;

    public FavoriteId() {}

    public FavoriteId(User user, Offer offer) {
        this.user = user;
        this.offer = offer;
    }
}
