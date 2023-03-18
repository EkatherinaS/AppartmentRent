package com.rentappartment.server.model.Offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OfferDao {

    @Autowired
    private OfferRepository repository;
    private int largestId = -1;


    public void save(Offer offer) {
        offer.updateDateUpdated();
        if (offer.getId() == null) {
            if (largestId == -1) {
                largestId = getLargestId();
            }
            offer.setId(++largestId);
        }
        repository.save(offer);
    }

    public Offer saveIfNotExists(Offer newOffer) {
        Offer offerToSave = checkIfExists(newOffer);
        if (offerToSave == null) offerToSave = newOffer;
        save(offerToSave);
        return offerToSave;
    }

    public void delete(Offer offer) {
        repository.delete(offer);
    }

    public Offer findById(int id) {
        return repository.findById(id).orElse(null);
    }

    private Offer checkIfExists(Offer offerToCheck) {
        List<Offer> list = new ArrayList<>();
        Streamable.of(repository.findAll())
                .filter(offer ->
                                offer.getType().equals(offerToCheck.getType()) &&
                                offer.getArea().equals(offerToCheck.getArea()) &&
                                offer.getRoomNumber().equals(offerToCheck.getRoomNumber()) &&
                                offer.getPrice().equals(offerToCheck.getPrice()) &&
                                offer.getKitchenArea().equals(offerToCheck.getKitchenArea()) &&
                                offer.getFloor().equals(offerToCheck.getFloor()))
                .forEach(list::add);
        if(list.size() > 0) {
            return list.get(0);
        }
        else return null;
    }

    public List<Offer> getAllOffers() {
        List<Offer> list = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(list::add);
        return list;
    }

    public int getLargestId() {
        return getAllOffers().stream().map(Offer::getId).max(Integer::compare).orElse(0);
    }

    public void deleteOldOffers(Date date) {
        Streamable.of(repository.findAll()).filter(offer -> offer.getDateUpdated()
                .compareTo(date) < 0).forEach(this::delete);
    }

    public List<Offer> getFilteredOffers(boolean flat, boolean room,
                                         int priceMin, int priceMax,
                                         boolean studio,
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
                        (offer.getRoomNumber() == 0 && studio) &&
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
