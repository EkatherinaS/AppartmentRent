package com.rentappartment.server.model.Image;

import com.rentappartment.server.model.Offer.Offer;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageDao {

    @Autowired
    private ImageRepository repository;

    public void save(Image address) {
        repository.save(address);
    }

    public void delete(Image address) {
        repository.delete(address);
    }

    public List<Image> getAllImages() {
        List<Image> list = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(list::add);
        return list;
    }

    public void deleteAllImages() {
        repository.deleteAll(Streamable.of(repository.findAll()));
    }

    public Image findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Image> getOfferImages(int id) {
        List<Image> list = new ArrayList<>();
        Streamable.of(repository.findAll()).filter(image -> image.getOffer().getId() == id).forEach(list::add);
        return list;
    }
}
