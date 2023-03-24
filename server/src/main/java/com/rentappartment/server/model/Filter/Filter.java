package com.rentappartment.server.model.Filter;

import com.rentappartment.server.model.Contact.Contact;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "\"Filter\"")
public class Filter implements Comparable<Filter> {
    @Id
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "sortAscending", nullable = false)
    private boolean sortAscending;
    @Column(name = "priority", nullable = false)
    private int priority;
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

    public Filter() {}

    public Filter(String name) {
        this.name = name;
        this.sortAscending = true;
    }

    @Override
    public int compareTo(Filter f) {
        return Integer.compare(getPriority(), f.getPriority());
    }
    @Override
    public boolean equals(Object object) {
        Filter a = (Filter) object;
        return this.name.equals(a.getName()) && this.priority == a.priority && this.sortAscending == a.sortAscending;
    }
}
