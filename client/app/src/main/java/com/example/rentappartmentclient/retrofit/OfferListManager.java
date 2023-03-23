package com.example.rentappartmentclient.retrofit;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.rentappartmentclient.HomeFragment;
import com.example.rentappartmentclient.model.OfferFilters;
import com.example.rentappartmentclient.model.database.Filter;
import com.example.rentappartmentclient.model.database.Offer;
import com.example.rentappartmentclient.model.database.User;
import com.example.rentappartmentclient.retrofit.api.FavoriteApi;
import com.example.rentappartmentclient.retrofit.api.OfferApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferListManager extends Observable {
    private final Context context;
    private final OfferApi offerApi;
    private List<Offer> offerList;


    public OfferListManager(Context context) {
        this.context = context;
        this.offerList = new ArrayList<>();
        this.offerApi = RetrofitService.getRetrofit().create(OfferApi.class);
    }

    public List<Offer> getOfferList() {
        return offerList;
    }

    private void updateOfferList(List<Offer> offerList) {
        this.offerList = offerList;
        setChanged();
        notifyObservers();
    }

    public void loadOffers() {
        offerApi.getAllOffers()
                .enqueue(new Callback<List<Offer>>() {
                    @Override
                    public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                        if (response.body() != null) {
                            updateOfferList(response.body());
                            Log.i("OfferListManager", "OfferList loaded: onResponse");
                        } else {
                            Log.w("OfferListManager", "OfferList loaded: onResponse null");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Offer>> call, Throwable t) {
                        Log.w("OfferListManager", "OfferList loaded: onFailure");
                    }
                });
    }

    public void loadSortedFilteredOffers(boolean flat, boolean room, int priceMin, int priceMax,
                                         boolean studio, int roomNumberMin, int roomNumberMax,
                                         int areaMin, int areaMax, int kitchenMin, int kitchenMax,
                                         int yearMin, int yearMax, int floorMin, int floorMax,
                                         int floorNumberMin, int floorNumberMax,
                                         List<Filter> prioritizedFilters) {
        offerApi.getFilteredSortedOffers(flat, room, priceMin, priceMax, studio, roomNumberMin,
                        roomNumberMax, areaMin, areaMax, kitchenMin, kitchenMax, yearMin,
                        yearMax, floorMin, floorMax, floorNumberMin, floorNumberMax,
                        prioritizedFilters)
                .enqueue(new Callback<List<Offer>>() {
                    @Override
                    public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                        if (response.body() != null) {
                            updateOfferList(response.body());
                            Log.i("OfferListManager", "SortedFilteredOffers loaded: onResponse");
                        } else {
                            Log.w("OfferListManager", "SortedFilteredOffers loaded: onResponse null");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Offer>> call, Throwable t) {
                        Log.w("OfferListManager", "SortedFilteredOffers loaded: onFailure");
                    }
                });

    }

    public void loadSortedOffers(List<Filter> prioritizedFilters) {
        offerApi.getSortedOffers(prioritizedFilters)
                .enqueue(new Callback<List<Offer>>() {
                    @Override
                    public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                        if (response.body() != null) {
                            updateOfferList(response.body());
                            Log.i("OfferListManager", "SortedOffers loaded: onResponse");
                        } else {
                            Log.w("OfferListManager", "SortedOffers loaded: onResponse null");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Offer>> call, Throwable t) {
                        Log.w("OfferListManager", "SortedOffers loaded: onFailure");
                    }
                });
    }

    public void loadFilteredOffers(boolean flat, boolean room, int priceMin, int priceMax,
                                   boolean studio, int roomNumberMin, int roomNumberMax,
                                   int areaMin, int areaMax, int kitchenMin, int kitchenMax,
                                   int yearMin, int yearMax, int floorMin, int floorMax,
                                   int floorNumberMin, int floorNumberMax) {
        offerApi.getFilteredOffers(flat, room, priceMin, priceMax, studio, roomNumberMin,
                        roomNumberMax, areaMin, areaMax, kitchenMin, kitchenMax, yearMin,
                        yearMax, floorMin, floorMax, floorNumberMin, floorNumberMax)
                .enqueue(new Callback<List<Offer>>() {
                    @Override
                    public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                        if (response.body() != null) {
                            updateOfferList(response.body());
                            Log.i("OfferListManager", "FilteredOffers loaded: onResponse");
                        } else {
                            Log.w("OfferListManager", "FilteredOffers loaded: onResponse null");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Offer>> call, Throwable t) {
                        Log.w("OfferListManager", "FilteredOffers loaded: onFailure");
                    }
                });
    }
}
