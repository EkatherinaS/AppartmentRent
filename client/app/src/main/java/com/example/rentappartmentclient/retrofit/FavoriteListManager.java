package com.example.rentappartmentclient.retrofit;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.rentappartmentclient.model.database.Favorite;
import com.example.rentappartmentclient.model.database.Offer;
import com.example.rentappartmentclient.model.database.User;
import com.example.rentappartmentclient.retrofit.api.FavoriteApi;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteListManager extends Observable {
    private final FavoriteApi favoriteApi;
    private final User user;
    private List<Offer> favoriteList;


    public FavoriteListManager(User user) {
        this.user = user;
        this.favoriteList = new ArrayList<>();
        this.favoriteApi = RetrofitService.getRetrofit().create(FavoriteApi.class);
    }

    public List<Offer> getFavoriteList() {
        return favoriteList;
    }

    public void loadFavoriteList() {
        favoriteApi.getFavoriteByUser(user.getUserId())
                .enqueue(new Callback<List<Offer>>() {
                    @Override
                    public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                        if (response.body() != null) {
                            updateFavoriteList(response.body());
                            Log.i("FavoriteListManager", "FavoriteList loaded: onResponse");
                        } else {
                            Log.w("FavoriteListManager", "Error loading FavoriteList: onResponse null");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Offer>> call, Throwable t) {
                        Log.w("FavoriteListManager", "Error loading FavoriteList: onFailure");
                    }
                });
    }


    private void updateFavoriteList(List<Offer> favoriteList) {
        this.favoriteList = favoriteList;
        setChanged();
        notifyObservers();
        Log.i("FavoriteListManager", "FavoriteList updated");
    }


    public boolean checkIfFavorite(Offer offer) {
        for(Offer favorite:favoriteList){
            if (Objects.equals(favorite.getId(), offer.getId())) {
                return true;
            }
        }
        return false;
    }


    public void saveFavorite(Favorite favorite) {
        favoriteApi.save(favorite)
                .enqueue(new Callback<Favorite>() {
                    @Override
                    public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                        if (response.body() != null) {
                            addFavorite(response.body().getOffer());
                            Log.i("FavoriteListManager", "Added to favorite: onResponse");
                        } else {
                            Log.w("FavoriteListManager", "Error adding to favorite: onResponse null");
                        }
                    }

                    @Override
                    public void onFailure(Call<Favorite> call, Throwable t) {
                        Log.w("FavoriteListManager", "Error adding to favorite: onFailure");
                    }
                });
    }

    public void deleteFavorite(Favorite favorite) {
        favoriteApi.delete(favorite)
                .enqueue(new Callback<Favorite>() {
                    @Override
                    public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                        if (response.body() != null) {
                            deleteFavorite(response.body().getOffer());
                            Log.i("FavoriteListManager", "Deleted from favorite: onResponse");
                        } else {
                            Log.w("FavoriteListManager", "Error deleting from favorite: onResponse null");
                        }

                    }

                    @Override
                    public void onFailure(Call<Favorite> call, Throwable t) {
                        Log.w("FavoriteListManager", "Error deleting from favorite: onFailure");
                    }
                });
    }


    private void addFavorite(Offer offer) {
        favoriteList.add(offer);
        setChanged();
        notifyObservers();
        Log.i("FavoriteListManager", "Added to favorite: " + offer.getId());
    }

    private void deleteFavorite(Offer offer) {
        for(Offer offerIterator: favoriteList) {
            if(Objects.equals(offer.getId(), offerIterator.getId())) {
                favoriteList.remove(offerIterator);
                break;
            }
        }
        setChanged();
        notifyObservers();
        Log.i("FavoriteListManager", "Deleted from favorite: " + offer.getId());
    }
}
