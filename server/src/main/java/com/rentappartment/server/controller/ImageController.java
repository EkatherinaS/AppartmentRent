package com.rentappartment.server.controller;

import com.rentappartment.server.model.Image.Image;
import com.rentappartment.server.model.Image.ImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageController {
    @Autowired
    private ImageDao imageDao;

    @GetMapping("/image/get-all")
    public List<Image> getAllImages() {
        return imageDao.getAllImages();
    }

    @GetMapping("/image/get-by-offer")
    public List<Image> getOfferImages(@RequestParam int id) {
        return imageDao.getOfferImages(id);
    }
}
