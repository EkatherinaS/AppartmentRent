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

    public Offer findById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Offer> getAllOffers() {
        List<Offer> list = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(list::add);
        return list;
    }

    public void deleteAllOffers() {
        repository.deleteAll();
    }

    public List<Offer> getFilteredOffers(boolean flat, boolean room,
                                         int priceMin, int priceMax,
                                         int roomNumberMin, int roomNumberMax,
                                         int areaMin, int areaMax,
                                         int kitchenMin, int kitchenMax,
                                         int yearMin, int yearMax,
                                         int floorMin, int floorMax,
                                         int floorNumberMin, int floorNumberMax) {
        String typeFlat = "Квартира";
        String typeRoom = "Комната";
        List<Offer> list = new ArrayList<>();
        Streamable.of(repository.findAll())
                .filter(offer ->
                        ((offer.getType().equals(typeFlat) == flat && flat) ||
                                (offer.getType().equals(typeRoom) == room && room)) &&
                        offer.getPrice() >= priceMin &&
                        offer.getPrice() <= priceMax &&
                        offer.getRoomNumber() >= roomNumberMin &&
                        offer.getRoomNumber() <= roomNumberMax &&
                        offer.getArea() >= areaMin &&
                        offer.getArea() <= areaMax &&
                        offer.getKitchenArea() >= kitchenMin &&
                        offer.getKitchenArea() <= kitchenMax &&
                        offer.getAddress().getYear() >= yearMin &&
                        offer.getAddress().getYear() <= yearMax &&
                        offer.getFloor() >= floorMin &&
                        offer.getFloor() <= floorMax &&
                        offer.getAddress().getFloorNumber() >= floorNumberMin &&
                        offer.getAddress().getFloorNumber() <= floorNumberMax)
                .forEach(list::add);
        return list;
    }

}
