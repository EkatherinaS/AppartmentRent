package com.example.rentappartmentclient.model;

import java.util.Objects;

public class Filter {

    private static int idNext = 0;
    private String name;
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

    public void sortAscending() {
        sortAscending = true;
    }

    public void sortDescending() {
        sortAscending = false;
    }

    public Filter(String name) {
        this.name = name;
        this.sortAscending = true;
        this.id = idNext;
        idNext++;
    }
}
