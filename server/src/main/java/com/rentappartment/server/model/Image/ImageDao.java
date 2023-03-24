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

    public void save(Image image) {
        repository.save(image);
    }

    public void delete(Image image) {
        repository.delete(image);
    }

    public List<Image> getAllImages() {
        List<Image> list = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(list::add);
        return list;
    }

    public void deleteAllImages() {
        repository.deleteAll();
    }

    public Image findById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Image> getOfferImages(int id) {
        List<Image> list = new ArrayList<>();
        Streamable.of(repository.findAll()).filter(image -> image.getOffer().getId() == id).forEach(list::add);
        return list;
    }
}
