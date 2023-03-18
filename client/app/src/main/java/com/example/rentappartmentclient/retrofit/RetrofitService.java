package com.example.rentappartmentclient.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static Retrofit retrofit;
    private static RetrofitService instance;

    private RetrofitService() {};

    private static Retrofit getRetrofitService() {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8080")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = getRetrofitService();
        }
        return retrofit;
    }
}
