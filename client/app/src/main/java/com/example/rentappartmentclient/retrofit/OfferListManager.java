package com.example.rentappartmentclient.retrofit;

import android.content.Context;
import android.widget.Toast;

import com.example.rentappartmentclient.HomeFragment;
import com.example.rentappartmentclient.model.OfferFilters;
import com.example.rentappartmentclient.model.database.Offer;
import com.example.rentappartmentclient.retrofit.api.OfferApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferListManager {
    private List<Offer> offerList;
    private static Context context;
    private static HomeFragment homeFragment;
    private static OfferListManager instance;


    public static void setContext(Context context, HomeFragment homeFragment) {
        OfferListManager.context = context;
        OfferListManager.homeFragment = homeFragment;
    }

    public static OfferListManager getInstance() {
        if (instance == null) {
            instance = new OfferListManager();
        }
        return instance;
    }

    public List<Offer> getOfferList() {
        return offerList;
    }

    public void loadOffers() {
        OfferApi offerApi = RetrofitService.getRetrofit().create(OfferApi.class);
        offerApi.getAllOffers()
                .enqueue(new Callback<List<Offer>>() {
                    @Override
                    public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                        offerList = response.body();
                        homeFragment.populateListView(offerList);
                    }

                    @Override
                    public void onFailure(Call<List<Offer>> call, Throwable t) {
                        Toast.makeText(context, "Loading offers failed", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void loadFilteredOffers() {
        OfferApi offerApi = RetrofitService.getRetrofit().create(OfferApi.class);
        offerApi.getFilteredOffers(OfferFilters.flat, OfferFilters.room, OfferFilters.priceMin,
                        OfferFilters.priceMax, OfferFilters.studio, OfferFilters.roomNumberMin,
                        OfferFilters.roomNumberMax, OfferFilters.areaMin, OfferFilters.areaMax,
                        OfferFilters.kitchenMin, OfferFilters.kitchenMax, OfferFilters.yearMin,
                        OfferFilters.yearMax, OfferFilters.floorMin, OfferFilters.floorMax,
                        OfferFilters.floorNumberMin, OfferFilters.floorNumberMax)
                .enqueue(new Callback<List<Offer>>() {
                    @Override
                    public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                        offerList = response.body();
                        homeFragment.populateListView(offerList);
                    }

                    @Override
                    public void onFailure(Call<List<Offer>> call, Throwable t) {
                        Toast.makeText(context, "Loading filtered offers failed", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
