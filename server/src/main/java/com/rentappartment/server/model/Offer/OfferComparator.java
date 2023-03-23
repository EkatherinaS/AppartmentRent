package com.rentappartment.server.model.Offer;

import com.rentappartment.server.model.Filter.Filter;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OfferComparator  implements Comparator<Offer> {

    private final List<Filter> prioritizedFilters;

    public OfferComparator (List<Filter> filters) {
        prioritizedFilters = filters;
    }

    @Override
    public int compare(Offer o1, Offer o2) {
        CompareToBuilder compareToBuilder = new CompareToBuilder();
        Collections.sort(prioritizedFilters);
        Offer offer1, offer2;
        for (Filter filter:prioritizedFilters) {
            if (filter.isSortAscending()) {
                offer1 = o1;
                offer2 = o2;
            }
            else {
                offer1 = o2;
                offer2 = o1;
            }
            switch (filter.getName()) {
                case ("Тип помещения") ->
                        compareToBuilder.append(offer1.getType(), offer2.getType());
                case ("Стоимость") ->
                        compareToBuilder.append(offer1.getPrice(), offer2.getPrice());
                case ("Количество комнат") ->
                        compareToBuilder.append(offer1.getRoomNumber(), offer2.getRoomNumber());
                case ("Общая площадь") ->
                        compareToBuilder.append(offer1.getArea(), offer2.getArea());
                case ("Площадь кухни") ->
                        compareToBuilder.append(offer1.getKitchenArea(), offer2.getKitchenArea());
                case ("Год постройки") ->
                        compareToBuilder.append(offer1.getAddress().getYear(), offer2.getAddress().getYear());
                case ("Этаж") ->
                        compareToBuilder.append(offer1.getFloor(), offer2.getFloor());
                case ("Количество этажей") ->
                        compareToBuilder.append(offer1.getAddress().getFloorNumber(), offer2.getAddress().getFloorNumber());
            }
        }
        return compareToBuilder.build();
    }
}
