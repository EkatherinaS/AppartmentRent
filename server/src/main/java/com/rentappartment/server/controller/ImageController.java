package com.rentappartment.server.controller;

import com.rentappartment.server.model.Filter.Filter;
import com.rentappartment.server.model.Image.Image;
import com.rentappartment.server.model.Image.ImageDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageController {
    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageDao imageDao;

    @GetMapping("/image/get-all")
    public List<Image> getAllImages() {
        List<Image> list = imageDao.getAllImages();
        logger.info("getAllImages finished, objects found: " + list.size());
        return list;
    }

    @GetMapping("/image/get-by-offer")
    public List<Image> getOfferImages(@RequestParam(name="offerId") int offerId) {
        List<Image> list = imageDao.getOfferImages(offerId);
        logger.info("getOfferImages finished, objects found: " + list.size() + " offerID:" + offerId);
        return list;
    }
}
