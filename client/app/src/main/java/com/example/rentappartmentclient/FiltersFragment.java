package com.example.rentappartmentclient;

import static java.lang.Math.max;
import static java.lang.Math.min;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.rentappartmentclient.model.OfferFilters;


public class FiltersFragment extends Fragment {

    private View view;
    private Context context;


    private CheckBox cbTypeFlat;
    private CheckBox cbTypeRoom;
    private EditText etPriceMin;
    private EditText etPriceMax;
    private CheckBox cbTypeStudio;
    private EditText etRoomNumberMin;
    private EditText etRoomNumberMax;
    private EditText etSpaceMin;
    private EditText etSpaceMax;
    private EditText etKitchenSpaceMin;
    private EditText etKitchenSpaceMax;
    private EditText etYearMin;
    private EditText etYearMax;
    private EditText etFloorMin;
    private EditText etFloorMax;
    private EditText etFloorNumberMin;
    private EditText etFloorNumberMax;
    private CheckBox cbWebsiteEtagi;
    private CheckBox cbWebsiteN1;

    public FiltersFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_filters, container, false);
        context = view.getContext();
        OfferFilters.setValues();

        cbTypeFlat = view.findViewById(R.id.cbTypeFlat);
        cbTypeRoom = view.findViewById(R.id.cbTypeRoom);
        etPriceMin = view.findViewById(R.id.etPriceMin);
        etPriceMax = view.findViewById(R.id.etPriceMax);
        cbTypeStudio = view.findViewById(R.id.cbTypeStudio);
        etRoomNumberMin = view.findViewById(R.id.etRoomNumberMin);
        etRoomNumberMax = view.findViewById(R.id.etRoomNumberMax);
        etSpaceMin = view.findViewById(R.id.etSpaceMin);
        etSpaceMax = view.findViewById(R.id.etSpaceMax);
        etKitchenSpaceMin = view.findViewById(R.id.etKitchenSpaceMin);
        etKitchenSpaceMax = view.findViewById(R.id.etKitchenSpaceMax);
        etYearMin = view.findViewById(R.id.etYearMin);
        etYearMax = view.findViewById(R.id.etYearMax);
        etFloorMin = view.findViewById(R.id.etFloorMin);
        etFloorMax = view.findViewById(R.id.etFloorMax);
        etFloorNumberMin = view.findViewById(R.id.etFloorNumberMin);
        etFloorNumberMax = view.findViewById(R.id.etFloorNumberMax);
        cbWebsiteEtagi = view.findViewById(R.id.cbWebsiteEtagi);
        cbWebsiteN1 = view.findViewById(R.id.cbWebsiteN1);

        cbTypeFlat.setChecked(OfferFilters.flat);
        cbTypeRoom.setChecked(OfferFilters.room);
        cbTypeStudio.setChecked(OfferFilters.studio);
        cbWebsiteEtagi.setChecked(OfferFilters.websiteEtagi);
        cbWebsiteEtagi.setChecked(OfferFilters.websiteN1);
        cbWebsiteN1.setEnabled(false);

        etPriceMin.setText(String.valueOf(OfferFilters.priceMin));
        etPriceMax.setText(String.valueOf(OfferFilters.priceMax));
        etRoomNumberMin.setText(String.valueOf(OfferFilters.roomNumberMin));
        etRoomNumberMax.setText(String.valueOf(OfferFilters.roomNumberMax));
        etSpaceMin.setText(String.valueOf(OfferFilters.areaMin));
        etSpaceMax.setText(String.valueOf(OfferFilters.areaMax));
        etKitchenSpaceMin.setText(String.valueOf(OfferFilters.kitchenMin));
        etKitchenSpaceMax.setText(String.valueOf(OfferFilters.kitchenMax));
        etYearMin.setText(String.valueOf(OfferFilters.yearMin));
        etYearMax.setText(String.valueOf(OfferFilters.yearMax));
        etFloorMin.setText(String.valueOf(OfferFilters.floorMin));
        etFloorMax.setText(String.valueOf(OfferFilters.floorMax));
        etFloorNumberMin.setText(String.valueOf(OfferFilters.floorNumberMin));
        etFloorNumberMax.setText(String.valueOf(OfferFilters.floorNumberMax));

        etPriceMin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editTextListener(v, hasFocus, OfferFilters.priceMin);
            }
        });
        etPriceMax.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editTextListener(v, hasFocus, OfferFilters.priceMax);
            }
        });
        etRoomNumberMin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editTextListener(v, hasFocus, OfferFilters.roomNumberMin);
            }
        });
        etRoomNumberMax.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editTextListener(v, hasFocus, OfferFilters.roomNumberMax);
            }
        });
        etSpaceMin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editTextListener(v, hasFocus, OfferFilters.areaMin);
            }
        });
        etSpaceMax.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editTextListener(v, hasFocus, OfferFilters.areaMax);
            }
        });
        etKitchenSpaceMin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editTextListener(v, hasFocus, OfferFilters.kitchenMin);
            }
        });
        etKitchenSpaceMax.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editTextListener(v, hasFocus, OfferFilters.kitchenMax);
            }
        });
        etYearMin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editTextListener(v, hasFocus, OfferFilters.yearMin);
            }
        });
        etYearMax.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editTextListener(v, hasFocus, OfferFilters.yearMax);
            }
        });
        etFloorMin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editTextListener(v, hasFocus, OfferFilters.floorMin);
            }
        });
        etFloorMax.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editTextListener(v, hasFocus, OfferFilters.floorMax);
            }
        });
        etFloorNumberMin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editTextListener(v, hasFocus, OfferFilters.floorNumberMin);
            }
        });
        etFloorNumberMax.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editTextListener(v, hasFocus, OfferFilters.floorNumberMax);
            }
        });

        return view;
    }

    private void editTextListener(View v, boolean hasFocus, int defaultValue) {
        if (hasFocus) {
            ((EditText) v).setText("");
        } else {
            if (((EditText) v).getText().toString().equals("")) {
                ((EditText) v).setText(String.valueOf(defaultValue));
            }
        }
    }
}