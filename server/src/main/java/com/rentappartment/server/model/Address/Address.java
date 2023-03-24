package com.rentappartment.server.model.Address;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Address\"")
public class Address{
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
    @Override
    public boolean equals(Object object) {
        Address a = (Address) object;
        if (this.address.equals(a.address) && this.year.equals(a.year) && this.floorNumber.equals(a.floorNumber)) {
            return true;
        }
        return false;
    }
}