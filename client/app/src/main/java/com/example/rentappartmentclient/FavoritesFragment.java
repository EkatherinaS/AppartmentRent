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
import com.example.rentappartmentclient.model.database.Offer;

import java.util.List;

public class FavoritesFragment extends Fragment {

    RecyclerView rvFavoriteOffers;
    ItemClickListener itemClickListener;
    OfferFragment offerFragment;
    Context context;

    public FavoritesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_favorites, container, false);
        context = mainView.getContext();

        rvFavoriteOffers = mainView.findViewById(R.id.rvFavoriteOffers);
        rvFavoriteOffers.setLayoutManager(new LinearLayoutManager(context));
        populateListView(DataManager.getInstance().getFavoriteList());
        return mainView;
    }

    public void populateListView(List<Offer> favoriteOfferList) {
        if (rvFavoriteOffers != null) {
            itemClickListener=new ItemClickListener() {
                @Override
                public void onClick(int position, Offer value) {
                    offerFragment = new OfferFragment(favoriteOfferList.get(position));
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, offerFragment)
                            .addToBackStack(null)
                            .commit();
                }
            };
            OfferAdapter offerAdapter = new OfferAdapter(favoriteOfferList, itemClickListener);
            rvFavoriteOffers.setAdapter(offerAdapter);
        }
    }
}