package com.rentappartment.server.model.Offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferDao {

    @Autowired
    private OfferRepository repository;

    public void save(Offer offer) {
        repository.save(offer);
    }

    public void delete(Offer offer) {
        repository.delete(offer);
    }

    public List<Offer> getAllOffers() {
        List<Offer> list = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(list::add);
        return list;
    }

    public List<Offer> getFavoriteOffers() {
        List<Offer> list = new ArrayList<>();
        Streamable.of(repository.findAll()).filter(Offer::getFavorite).forEach(list::add);
        return list;
    }

    public List<Offer> getFilteredOffers(int floorMin, int floorMax,
                                         int kitchenMin, int kitchenMax,
                                         int areaMin, int areaMax) {
        List<Offer> list = new ArrayList<>();
        Streamable.of(repository.findAll())
                .filter(offer ->
                        offer.getFloor() >= floorMin &&
                        offer.getFloor() <= floorMax &&
                        offer.getKitchenArea() >= kitchenMin &&
                        offer.getKitchenArea() <= kitchenMax &&
                        offer.getArea() >= areaMin &&
                        offer.getArea() <= areaMax)
                .forEach(list::add);
        return list;
    }

    public Offer findById(int id) {
        return repository.findById(id).orElse(null);
    }
}
