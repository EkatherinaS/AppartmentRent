package com.example.rentappartmentclient.retrofit;

import com.example.rentappartmentclient.model.Offer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OfferApi {

    @GET("/offer/get-all")
    Call<List<Offer>> getAllOffers();

    /*@POST("/offer/save")
    Call<Offer> save(@Body Offer offer);*/

}
