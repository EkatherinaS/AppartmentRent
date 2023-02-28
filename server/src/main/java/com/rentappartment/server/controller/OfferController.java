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

    @GetMapping("/offer/get-filtered")
    public List<Offer> getFilteredOffers(boolean flat, boolean room,
                                         int priceMin, int priceMax,
                                         int roomNumberMin, int roomNumberMax,
                                         int areaMin, int areaMax,
                                         int kitchenMin, int kitchenMax,
                                         int yearMin, int yearMax,
                                         int floorMin, int floorMax,
                                         int floorNumberMin, int floorNumberMax) {
        return offerDao.getFilteredOffers(flat, room, priceMin, priceMax,
                roomNumberMin, roomNumberMax, areaMin, areaMax,
                kitchenMin, kitchenMax, yearMin, yearMax,
                floorMin, floorMax, floorNumberMin, floorNumberMax);
    }
}
