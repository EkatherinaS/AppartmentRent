package com.example.rentappartmentclient.retrofit;

import com.example.rentappartmentclient.model.Image;
import com.example.rentappartmentclient.model.Offer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OfferApi {

    @GET("/offer/get-all")
    Call<List<Offer>> getAllOffers();

    @GET("/offer/get-favorites")
    Call<List<Offer>> getFavoriteOffers();

    @GET("/image/get-by-offer")
    Call<List<Image>> getImagesByOffer(@Query("offerId") int offerId);


    /*@POST("/offer/save")
    Call<Offer> save(@Body Offer offer);*/

}
