package com.example.rentappartmentclient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FiltersFragment extends Fragment {

    private static final boolean flat = true;
    private static final boolean room = true;
    private static final int priceMin = 0;
    private static final int priceMax = -1;
    private static final int roomNumberMin = 0;
    private static final int roomNumberMax = -1;
    private static final int areaMin = 0;
    private static final int areaMax = -1;
    private static final int kitchenMin = 0;
    private static final int kitchenMax = -1;
    private static final int yearMin = 1700;
    private static final int yearMax = -1;
    private static final int floorMin = 1;
    private static final int floorMax = -1;
    private static final int floorNumberMin = 1;
    private static final int floorNumberMax = -1;

    public FiltersFragment() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filters, container, false);
    }
}