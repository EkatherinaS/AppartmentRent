package com.example.rentappartmentclient.retrofit;

import android.content.Context;
import android.widget.Toast;

import com.example.rentappartmentclient.HomeFragment;
import com.example.rentappartmentclient.model.OfferFilters;
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
                            Toast.makeText(context, "Загружен список предложений", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Ошибка загрузки списка предложений", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Offer>> call, Throwable t) {
                        Toast.makeText(context, "Ошибка загрузки списка предложений", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(context, "Загружен список предложений с фильтрами", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Ошибка загрузки списка предложений с фильтрами", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Offer>> call, Throwable t) {
                        Toast.makeText(context, "Ошибка загрузки с фильтрами", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void loadSortedOffers() {

    }
}
