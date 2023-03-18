package com.example.rentappartmentclient.retrofit;

import android.content.Context;
import android.widget.Toast;

import com.example.rentappartmentclient.model.PreferenceManager;
import com.example.rentappartmentclient.model.database.User;
import com.example.rentappartmentclient.retrofit.api.FavoriteApi;
import com.example.rentappartmentclient.retrofit.api.UserApi;

import java.util.ArrayList;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserManager extends Observable {
    private final Context context;
    private final UserApi userApi;
    private final PreferenceManager preferenceManager;
    private User currentUser;


    public UserManager(Context context) {
        this.context = context;
        this.userApi = RetrofitService.getRetrofit().create(UserApi.class);
        this.preferenceManager = new PreferenceManager(context);
    }

    public void loadUser() {
        checkUser(preferenceManager.getUserId());
    }

    public User getCurrentUser() {
        return currentUser;
    }


    private void updateUser(User user) {
        preferenceManager.saveUserId(user.getUserId());
        currentUser = user;
        setChanged();
        notifyObservers();
    }

    private void checkUser(int userId) {
        userApi.checkUser(userId)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.body() != null) {
                            updateUser(response.body());
                            Toast.makeText(context, "Найден пользователь: " + response.body().getUserId(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Создан новый пользователь", Toast.LENGTH_LONG).show();
                            createUser();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(context, "Пользователь не найден", Toast.LENGTH_LONG).show();
                        createUser();
                    }
                });
    }

    private void createUser() {
        userApi.createUser()
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.body() != null) {
                            updateUser(response.body());
                            Toast.makeText(context, "Создан пользователь: " + response.body().getUserId(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Не удалось создать пользователя", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(context, "Не удалось создать пользователя", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
