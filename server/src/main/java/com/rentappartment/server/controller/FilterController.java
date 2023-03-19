package com.rentappartment.server.controller;

import com.rentappartment.server.model.Filter.Filter;
import com.rentappartment.server.model.Filter.FilterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilterController {

    @Autowired
    private FilterDao filterDao;

    @GetMapping("/filters/get-all")
    public List<Filter> getFilters() {
        return filterDao.getAllFilters();
    }
}
