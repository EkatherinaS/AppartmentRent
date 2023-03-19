package com.example.rentappartmentclient.model.database;

import java.util.Objects;

public class Filter implements Comparable<Filter> {

    private String name;
    private boolean sortAscending;
    private int priority;

    public Filter(String name, boolean sortAscending, int priority) {
        this.name = name;
        this.sortAscending = sortAscending;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!Objects.equals(name, "")) {
            this.name = name;
        }
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority >= 0) {
            this.priority = priority;
        }
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    public void setSortAscending(boolean isAscending) {
        this.sortAscending = isAscending;
    }

    public void sortAscending() {
        sortAscending = true;
    }

    public void sortDescending() {
        sortAscending = false;
    }

    @Override
    public int compareTo(Filter f) {
        return Integer.compare(getPriority(), f.getPriority());
    }
}
