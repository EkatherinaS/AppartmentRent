package com.example.rentappartmentclient.retrofit.api;

import com.example.rentappartmentclient.model.database.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserApi {

    @GET("/user/check")
    Call<User> checkUser(@Query("user_id") int userId);

    @GET("/user/create")
    Call<User> createUser();
}
