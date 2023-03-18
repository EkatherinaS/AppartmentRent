package com.rentappartment.server.controller;

import com.rentappartment.server.model.Offer.Offer;
import com.rentappartment.server.model.Offer.OfferDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/offer/get-filtered")
    public List<Offer> getFilteredOffers(@RequestParam(name="flat") boolean flat,
                                         @RequestParam(name="room") boolean room,
                                         @RequestParam(name="priceMin") int priceMin,
                                         @RequestParam(name="priceMax") int priceMax,
                                         @RequestParam(name="studio") boolean studio,
                                         @RequestParam(name="roomNumberMin") int roomNumberMin,
                                         @RequestParam(name="roomNumberMax") int roomNumberMax,
                                         @RequestParam(name="areaMin") int areaMin,
                                         @RequestParam(name="areaMax") int areaMax,
                                         @RequestParam(name="kitchenMin") int kitchenMin,
                                         @RequestParam(name="kitchenMax") int kitchenMax,
                                         @RequestParam(name="yearMin") int yearMin,
                                         @RequestParam(name="yearMax") int yearMax,
                                         @RequestParam(name="floorMin") int floorMin,
                                         @RequestParam(name="floorMax") int floorMax,
                                         @RequestParam(name="floorNumberMin") int floorNumberMin,
                                         @RequestParam(name="floorNumberMax") int floorNumberMax) {
        return offerDao.getFilteredOffers(flat, room, priceMin, priceMax, studio,
                roomNumberMin, roomNumberMax, areaMin, areaMax,
                kitchenMin, kitchenMax, yearMin, yearMax,
                floorMin, floorMax, floorNumberMin, floorNumberMax);
    }
}
