package com.example.rentappartmentclient.model;

import java.util.Objects;

public class Filter {

    private static int idNext = 0;
    private String name;
    private Object value;
    private Object defaultValue;
    private int id;
    private boolean sortAscending;

    public boolean isSortAscending() {
        return sortAscending;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!Objects.equals(name, "")) {
            this.name = name;
        }
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        if (value != null) {
            this.value = value;
        }
    }

    public void sortAscending() {
        sortAscending = true;
    }

    public void sortDescending() {
        sortAscending = false;
    }

    public Filter(String name, Object defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.sortAscending = true;
        this.id = idNext;
        idNext++;
    }
}
