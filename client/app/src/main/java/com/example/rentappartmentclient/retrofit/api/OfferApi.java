package com.example.rentappartmentclient.retrofit.api;

import com.example.rentappartmentclient.model.database.Filter;
import com.example.rentappartmentclient.model.database.Image;
import com.example.rentappartmentclient.model.database.Offer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OfferApi {

    @GET("/offer/get-all")
    Call<List<Offer>> getAllOffers();

    @GET("/image/get-by-offer")
    Call<List<Image>> getImagesByOffer(@Query("offerId") int offerId);

    @GET("/offer/get-filtered")
    Call<List<Offer>> getFilteredOffers(@Query("flat") boolean flat,
                                        @Query("room") boolean room,
                                        @Query("priceMin") int priceMin,
                                        @Query("priceMax") int priceMax,
                                        @Query("studio") boolean studio,
                                        @Query("roomNumberMin") int roomNumberMin,
                                        @Query("roomNumberMax") int roomNumberMax,
                                        @Query("areaMin") int areaMin,
                                        @Query("areaMax") int areaMax,
                                        @Query("kitchenMin") int kitchenMin,
                                        @Query("kitchenMax") int kitchenMax,
                                        @Query("yearMin") int yearMin,
                                        @Query("yearMax") int yearMax,
                                        @Query("floorMin") int floorMin,
                                        @Query("floorMax") int floorMax,
                                        @Query("floorNumberMin") int floorNumberMin,
                                        @Query("floorNumberMax") int floorNumberMax);


    @POST("/offer/get-filtered-sorted")
    Call<List<Offer>> getFilteredSortedOffers(@Query("flat") boolean flat,
                                        @Query("room") boolean room,
                                        @Query("priceMin") int priceMin,
                                        @Query("priceMax") int priceMax,
                                        @Query("studio") boolean studio,
                                        @Query("roomNumberMin") int roomNumberMin,
                                        @Query("roomNumberMax") int roomNumberMax,
                                        @Query("areaMin") int areaMin,
                                        @Query("areaMax") int areaMax,
                                        @Query("kitchenMin") int kitchenMin,
                                        @Query("kitchenMax") int kitchenMax,
                                        @Query("yearMin") int yearMin,
                                        @Query("yearMax") int yearMax,
                                        @Query("floorMin") int floorMin,
                                        @Query("floorMax") int floorMax,
                                        @Query("floorNumberMin") int floorNumberMin,
                                        @Query("floorNumberMax") int floorNumberMax,
                                        @Body List<Filter> prioritizedFilters);

    @POST("/offer/get-sorted")
    Call<List<Offer>> getSortedOffers(@Body List<Filter> prioritizedFilters);
}
