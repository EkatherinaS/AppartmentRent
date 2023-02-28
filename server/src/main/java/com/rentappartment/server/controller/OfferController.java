package com.rentappartment.server.controller;

import com.rentappartment.server.model.Offer.Offer;
import com.rentappartment.server.model.Offer.OfferDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OfferController {

    @Autowired
    private OfferDao offerDao;

    @GetMapping("/offer/get-all")
    public List<Offer> getAllOffers() {
        return offerDao.getAllOffers();
    }

    @GetMapping("/offer/get-favorites")
    public List<Offer> getFavoriteOffers() {
        return offerDao.getFavoriteOffers();
    }
}
