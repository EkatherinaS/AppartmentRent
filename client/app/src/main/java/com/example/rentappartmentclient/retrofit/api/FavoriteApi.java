package com.example.rentappartmentclient.retrofit.api;

import com.example.rentappartmentclient.model.database.Favorite;
import com.example.rentappartmentclient.model.database.Offer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FavoriteApi {

    @GET("/favorite/get-all")
    Call<List<Offer>> getAllOffers();

    @GET("/favorite/get-by-user")
    Call<List<Offer>> getFavoriteByUser(@Query("user_id") int userId);

    @POST("/favorite/save")
    Call<Favorite> save(@Body Favorite favorite);

    @POST("/favorite/delete")
    Call<Favorite> delete(@Body Favorite favorite);

}
