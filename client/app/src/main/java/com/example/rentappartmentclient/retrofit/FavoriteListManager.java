package com.example.rentappartmentclient.retrofit;

import android.content.Context;
import android.widget.Toast;

import com.example.rentappartmentclient.model.database.Favorite;
import com.example.rentappartmentclient.model.database.Offer;
import com.example.rentappartmentclient.retrofit.api.FavoriteApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteListManager {
    private List<Offer> favoriteList;
    private static Context context;
    private static FavoriteListManager instance;

    public static void setContext(Context context) {
        FavoriteListManager.context = context;
    }

    public static FavoriteListManager getInstance() {
        if (instance == null) {
            instance = new FavoriteListManager();
        }
        return instance;
    }

    public boolean checkIfFavorite(Offer offer) {
        if (favoriteList != null) {
            for(Offer favorite:favoriteList){
                if (Objects.equals(favorite.getId(), offer.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Offer> getFavoriteList() {
        return favoriteList;
    }

    public void addFavorite(Offer offer) {
        favoriteList.add(offer);
    }

    public void deleteFavorite(Offer offer) {
        favoriteList.remove(offer);
    }

    public void getAllFavorite(int userId) {
        FavoriteApi favoriteApi = RetrofitService.getRetrofit().create(FavoriteApi.class);
        favoriteApi.getFavoriteByUser(userId)
                .enqueue(new Callback<List<Offer>>() {
                    @Override
                    public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                        if (response.body() != null) {
                            favoriteList = response.body();
                            Toast.makeText(context, "Получен список избранного", Toast.LENGTH_LONG).show();
                        } else {
                            favoriteList = new ArrayList<>();
                            Toast.makeText(context, "Ошибка получения списка", Toast.LENGTH_LONG).show();
                        }
                        OfferListManager.getInstance().loadOffers();
                    }

                    @Override
                    public void onFailure(Call<List<Offer>> call, Throwable t) {
                        Toast.makeText(context, "Ошибка получения списка", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void saveFavorite(Favorite favorite) {
        FavoriteApi favoriteApi = RetrofitService.getRetrofit().create(FavoriteApi.class);
        favoriteApi.save(favorite)
                .enqueue(new Callback<Favorite>() {
                    @Override
                    public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                        if (response.body() != null) {
                            addFavorite(response.body().getOffer());
                            Toast.makeText(context, "Добавлено в избранное", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Ошибка добавления в избранное", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Favorite> call, Throwable t) {
                        Toast.makeText(context, "Ошибка добавления в избранное", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void deleteFavorite(Favorite favorite) {
        FavoriteApi favoriteApi = RetrofitService.getRetrofit().create(FavoriteApi.class);
        favoriteApi.delete(favorite)
                .enqueue(new Callback<Favorite>() {
                    @Override
                    public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                        if (response.body() != null) {
                            deleteFavorite(response.body().getOffer());
                            Toast.makeText(context, "Удалено из избранного", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Ошибка удаления из избранного", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Favorite> call, Throwable t) {
                        Toast.makeText(context, "Ошибка удаления из избранного", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
