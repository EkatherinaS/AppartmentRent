package com.example.rentappartmentclient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rentappartmentclient.adapter.RecyclerRowMoveCallback;
import com.example.rentappartmentclient.adapter.SortAdapter;
import com.example.rentappartmentclient.adapter.SortHolder;
import com.example.rentappartmentclient.model.database.Filter;
import com.example.rentappartmentclient.retrofit.DataManager;

import java.util.ArrayList;
import java.util.List;

public class SortFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;

    public SortFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sort, container, false);
        initializeRecyclerView();
        return view;
    }

    public List<Filter> getFilterList() {
        if (recyclerView != null) {
            List<Filter> list = new ArrayList<>();
            for (int i = 0; i < recyclerView.getChildCount(); i++) {
                SortHolder holder = (SortHolder) recyclerView.findViewHolderForAdapterPosition(i);
                if (holder != null) {
                    list.add(new Filter(holder.name.getText().toString(), !holder.sort.isChecked(), i));
                }
            }
            return list;
        }
        return DataManager.getInstance().getFilterList();
    }

    private void initializeRecyclerView() {
        recyclerView = view.findViewById(R.id.rvFilters);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        SortAdapter sortAdapter = new SortAdapter(DataManager.getInstance().getFilterList());
        ItemTouchHelper.Callback callback = new RecyclerRowMoveCallback(sortAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(sortAdapter);
    }
}