package com.rentappartment.server.model.Offer;

import com.rentappartment.server.model.Address.Address;
import com.rentappartment.server.model.Contact.Contact;
import com.rentappartment.server.model.Filter.Filter;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "\"Offer\"")
public class Offer {
    @Id
    @Column(name = "code", nullable = false)
    private Integer id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "room_number", nullable = false)
    private Integer roomNumber;

    @Column(name = "area", nullable = false)
    private Double area;

    @Column(name = "kitchen_area")
    private Double kitchenArea;

    @Column(name = "floor")
    private Integer floor;

    @Lob
    @Column(name = "full_description", nullable = false)
    private String fullDescription;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "main_image")
    private String mainImage;

    @Column(name = "date_updated")
    private Date dateUpdated;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contact", nullable = false)
    private Contact contact;

    public void updateDateUpdated() {
        dateUpdated = new Date();
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Double getKitchenArea() {
        return kitchenArea;
    }

    public void setKitchenArea(Double kitchenArea) {
        this.kitchenArea = kitchenArea;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        Offer a = (Offer) object;
        return Objects.equals(this.area, a.area) &&
                Objects.equals(this.type, a.type) &&
                Objects.equals(this.kitchenArea, a.kitchenArea) &&
                Objects.equals(this.floor, a.floor) &&
                Objects.equals(this.mainImage, a.mainImage) &&
                Objects.equals(this.fullDescription, a.fullDescription) &&
                Objects.equals(this.roomNumber, a.roomNumber) &&
                Objects.equals(this.price, a.price) &&
                this.address == a.address &&
                this.contact == a.contact;
    }
}