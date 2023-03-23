package com.example.rentappartmentclient.model;

import static java.lang.Math.max;
import static java.lang.Math.min;

import com.example.rentappartmentclient.model.database.Offer;
import com.example.rentappartmentclient.retrofit.DataManager;

public class OfferFilters {
    
    public static boolean flat = true;
    public static boolean room = true;
    public static boolean studio = true;
    public static int priceMin = Integer.MAX_VALUE;
    public static int priceMax = -1;
    public static int roomNumberMin = Integer.MAX_VALUE;
    public static int roomNumberMax = -1;
    public static int areaMin = Integer.MAX_VALUE;
    public static int areaMax = -1;
    public static int kitchenMin = Integer.MAX_VALUE;
    public static int kitchenMax = -1;
    public static int yearMin = Integer.MAX_VALUE;
    public static int yearMax = -1;
    public static int floorMin = Integer.MAX_VALUE;
    public static int floorMax = -1;
    public static int floorNumberMin = Integer.MAX_VALUE;
    public static int floorNumberMax = -1;


    public static void setValues() {

        for (Offer offer: DataManager.getInstance().getOfferList()) {
            priceMax = (int)max(offer.getPrice(), priceMax);
            priceMin = offer.getPrice() != -1 ?
                    (int)min(offer.getPrice(), priceMin) : priceMin;
            roomNumberMax = max(offer.getRoomNumber(), roomNumberMax);
            roomNumberMin = offer.getRoomNumber() != -1 ?
                    min(offer.getRoomNumber(), roomNumberMin) : roomNumberMin;
            areaMax = (int)max(offer.getArea(), areaMax);
            areaMin = offer.getArea() != -1 ?
                    (int)min(offer.getArea(), areaMin) : areaMin;
            kitchenMax = (int)max(offer.getKitchenArea(), kitchenMax);
            kitchenMin = offer.getKitchenArea() != -1 ?
                    (int)min(offer.getKitchenArea(), kitchenMin) : kitchenMin;
            yearMax = max(offer.getAddress().getYear(), yearMax);
            yearMin = offer.getAddress().getYear() != -1 ?
                    min(offer.getAddress().getYear(), yearMin) : yearMin;
            floorMax = max(offer.getFloor(), floorMax);
            floorMin = offer.getFloor() != -1 ?
                    min(offer.getFloor(), floorMin) : floorMin;
            floorNumberMax = max(offer.getAddress().getFloorNumber(), floorNumberMax);
            floorNumberMin = offer.getAddress().getFloorNumber() != -1 ?
                    min(offer.getAddress().getFloorNumber(), floorNumberMin) : floorNumberMin;
        }
    }
}
