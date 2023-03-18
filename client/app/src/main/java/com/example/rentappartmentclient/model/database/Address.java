package com.example.rentappartmentclient.model.database;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Address\"")
public class Address {
    @Id
    @Column(name = "text_address", nullable = false)
    private String address;

    @Column(name = "floor_number")
    private Integer floorNumber;

    @Column(name = "year")
    private Integer year;


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String textAddress) {
        this.address = textAddress;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }
}