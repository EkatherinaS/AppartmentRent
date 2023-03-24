package com.rentappartment.server.model.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterDao {

    @Autowired
    private FilterRepository repository;

    public void save(Filter filter) {
        repository.save(filter);
    }

    public void delete(Filter filter) {
        repository.delete(filter);
    }

    public List<Filter> getAllFilters() {
        List<Filter> list = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(list::add);
        return list;
    }

    public void deleteAllFilters() {
        repository.deleteAll();
    }

    public Filter findById(String name) {
        return repository.findById(name).orElse(null);
    }

    public void updateFilters() {
        deleteAllFilters();
        save(new Filter("Тип помещения"));
        save(new Filter("Стоимость"));
        save(new Filter("Количество комнат"));
        save(new Filter("Общая площадь"));
        save(new Filter("Площадь кухни"));
        save(new Filter("Год постройки"));
        save(new Filter("Этаж"));
        save(new Filter("Количество этажей"));
    }
}
