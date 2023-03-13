package com.example.rentappartmentclient;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rentappartmentclient.adapter.OfferAdapter;
import com.example.rentappartmentclient.adapter.ItemClickListener;
import com.example.rentappartmentclient.model.Offer;
import com.example.rentappartmentclient.retrofit.OfferApi;
import com.example.rentappartmentclient.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritesFragment extends Fragment {

    RecyclerView rvFavoriteOffers;
    ItemClickListener itemClickListener;
    OfferFragment offerFragment;
    Context context;

    public FavoritesFragment() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView = inflater.inflate(R.layout.fragment_favorites, container, false);
        context = mainView.getContext();

        rvFavoriteOffers = mainView.findViewById(R.id.rvFavoriteOffers);
        rvFavoriteOffers.setLayoutManager(new LinearLayoutManager(context));

        loadOffers();

        return mainView;
    }

    private void loadOffers() {
        RetrofitService retrofitService = new RetrofitService();
        OfferApi offerApi = retrofitService.getRetrofit().create(OfferApi.class);
        offerApi.getFavoriteOffers()
                .enqueue(new Callback<List<Offer>>() {
                    @Override
                    public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Offer>> call, Throwable t) {
                        Toast.makeText(context, "Loading offers failed", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void populateListView(List<Offer> favoriteOfferList) {
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
        OfferAdapter offerAdapter = new OfferAdapter(favoriteOfferList,itemClickListener);
        rvFavoriteOffers.setAdapter(offerAdapter);
    }
}