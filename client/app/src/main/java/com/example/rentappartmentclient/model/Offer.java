package com.example.rentappartmentclient.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "favorite", nullable = false)
    private Boolean favorite = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contact", nullable = false)
    private Contact contact;

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

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
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
}