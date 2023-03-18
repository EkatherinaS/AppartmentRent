package com.example.rentappartmentclient;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.rentappartmentclient.adapter.RecyclerRowMoveCallback;
import com.example.rentappartmentclient.adapter.SortAdapter;
import com.example.rentappartmentclient.model.Filter;

import java.util.ArrayList;

public class SortFragment extends Fragment {

    private View view;

    private ArrayList<Filter> list = new ArrayList<>();

    public SortFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sort, container, false);
        initializeRecyclerView();
        return view;
    }

    private void initializeRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.rvFilters);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        getFilters();
        SortAdapter sortAdapter = new SortAdapter(list);
        ItemTouchHelper.Callback callback = new RecyclerRowMoveCallback(sortAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(sortAdapter);
    }

    private void getFilters() {
        list.add(new Filter("Тип помещения"));
        list.add(new Filter("Стоимость"));
        list.add(new Filter("Количество комнат"));
        list.add(new Filter("Общая площадь"));
        list.add(new Filter("Площадь кухни"));
        list.add(new Filter("Год постройки"));
        list.add(new Filter("Этаж"));
        list.add(new Filter("Количество этажей"));
    }
}