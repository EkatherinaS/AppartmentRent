package com.example.rentappartmentclient.retrofit.api;

import com.example.rentappartmentclient.model.database.Filter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FilterApi {
    @GET("/filters/get-all")
    Call<List<Filter>> getAllFilters();
}
