package com.example.rentappartmentclient.retrofit;

import android.content.Context;
import android.widget.Toast;

import com.example.rentappartmentclient.model.database.Filter;
import com.example.rentappartmentclient.retrofit.api.FilterApi;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterManager  extends Observable {
    private final Context context;
    private final FilterApi filterApi;
    private List<Filter> filterList;


    public FilterManager(Context context) {
        this.context = context;
        this.filterApi = RetrofitService.getRetrofit().create(FilterApi.class);
    }

    public List<Filter> getFilterList() {
        return filterList;
    }


    private void updateFilterList(List<Filter> filterList) {
        this.filterList = filterList;
        setChanged();
        notifyObservers();
    }

    public void loadFilterList() {
        filterApi.getAllFilters()
                .enqueue(new Callback<List<Filter>>() {
                    @Override
                    public void onResponse(Call<List<Filter>> call, Response<List<Filter>> response) {
                        if (response.body() != null) {
                            updateFilterList(response.body());
                            Toast.makeText(context, "Загружены фильтры", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Ошибка загрузки фильтров", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Filter>> call, Throwable t) {
                        Toast.makeText(context, "Ошибка загрузки фильтров", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
