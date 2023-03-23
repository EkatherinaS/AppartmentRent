package com.rentappartment.server.controller;

import com.rentappartment.server.model.Filter.Filter;
import com.rentappartment.server.model.Filter.FilterDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilterController {
    private static final Logger logger = LoggerFactory.getLogger(FilterController.class);

    @Autowired
    private FilterDao filterDao;

    @GetMapping("/filters/get-all")
    public List<Filter> getFilters() {
        List<Filter> list = filterDao.getAllFilters();
        logger.info("getFilters finished, objects found: " + list.size());
        return list;
    }
}
