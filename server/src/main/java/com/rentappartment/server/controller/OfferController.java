package com.rentappartment.server.controller;

import com.rentappartment.server.model.Filter.Filter;
import com.rentappartment.server.model.Image.Image;
import com.rentappartment.server.model.Offer.Offer;
import com.rentappartment.server.model.Offer.OfferDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OfferController {
    private static final Logger logger = LoggerFactory.getLogger(OfferController.class);

    @Autowired
    private OfferDao offerDao;

    @GetMapping("/offer/get-all")
    public List<Offer> getAllOffers() {
        List<Offer> list = offerDao.getAllOffers();
        logger.info("getAllOffers finished, objects found: " + list.size());
        return list;
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
        List<Offer> list = offerDao.getFilteredOffers(flat, room, priceMin, priceMax, studio,
                roomNumberMin, roomNumberMax, areaMin, areaMax,
                kitchenMin, kitchenMax, yearMin, yearMax,
                floorMin, floorMax, floorNumberMin, floorNumberMax);
        logger.info("getFilteredOffers finished, objects found: " + list.size());
        return list;
    }

    @PostMapping("/offer/get-sorted")
    public List<Offer> getSortedOffers(@RequestBody List<Filter> prioritizedFilters) {
        List<Offer> list = offerDao.getSortedOffers(prioritizedFilters);
        logger.info("getSortedOffers finished, objects sorted: " + list.size());
        return list;
    }

    @PostMapping("/offer/get-filtered-sorted")
    public List<Offer> getFilteredSortedOffers(@RequestParam(name="flat") boolean flat,
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
                                               @RequestParam(name="floorNumberMax") int floorNumberMax,
                                               @RequestBody List<Filter> prioritizedFilters) {
        List<Offer> list = offerDao.getFilteredSortedOffers(flat, room, priceMin, priceMax, studio,
                roomNumberMin, roomNumberMax, areaMin, areaMax,
                kitchenMin, kitchenMax, yearMin, yearMax,
                floorMin, floorMax, floorNumberMin, floorNumberMax, prioritizedFilters);
        logger.info("getFilteredSortedOffers finished, objects processed: " + list.size());
        return list;
    }
}
