package com.example.rentappartmentclient;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rentappartmentclient.adapter.OfferAdapter;
import com.example.rentappartmentclient.adapter.ItemClickListener;
import com.example.rentappartmentclient.retrofit.DataManager;
import com.example.rentappartmentclient.retrofit.FavoriteListManager;
import com.example.rentappartmentclient.model.database.Offer;
import com.example.rentappartmentclient.retrofit.OfferListManager;

import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView rvOffers;
    ItemClickListener itemClickListener;
    OfferFragment offerFragment;

    public static Context context;

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_home, container, false);
        context = mainView.getContext();
        rvOffers = mainView.findViewById(R.id.rvOffers);
        rvOffers.setLayoutManager(new LinearLayoutManager(context));
        populateListView(DataManager.getInstance().getOfferList());

        return mainView;
    }

    public void populateListView(List<Offer> offerList) {
        if (rvOffers != null) {
            itemClickListener=new ItemClickListener() {
                @Override
                public void onClick(int position, Offer value) {
                    offerFragment = new OfferFragment(offerList.get(position));
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, offerFragment)
                            .addToBackStack(null)
                            .commit();
                }
            };
            OfferAdapter offerAdapter = new OfferAdapter(offerList, itemClickListener);
            rvOffers.setAdapter(offerAdapter);
        }
    }
}