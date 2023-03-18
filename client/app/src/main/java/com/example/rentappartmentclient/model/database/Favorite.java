package com.example.rentappartmentclient.model.database;

public class Favorite {
    private User user;
    private Offer offer;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

}
