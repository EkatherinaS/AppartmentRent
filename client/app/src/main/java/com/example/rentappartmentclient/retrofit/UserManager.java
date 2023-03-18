package com.example.rentappartmentclient.retrofit;

import android.content.Context;
import android.widget.Toast;

import com.example.rentappartmentclient.model.PreferenceManager;
import com.example.rentappartmentclient.model.database.User;
import com.example.rentappartmentclient.retrofit.api.UserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserManager {
    private User currentUser;
    private static Context context;
    private static UserManager instance;
    private final PreferenceManager preferenceManager;

    public static void setMainActivity(Context context) {
        UserManager.context = context;
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    private UserManager() {
        preferenceManager = new PreferenceManager(context);
    }

    public User getCurrentUser() {
        return currentUser;
    }


    public void checkUserFromPreferenceManager() {
        int userId = preferenceManager.getUserId();
        checkUser(userId);
    }

    private void updateUser(User user) {
        preferenceManager.saveUserId(user.getUserId());
        currentUser = user;
        FavoriteListManager.getInstance().getAllFavorite(user.getUserId());
    }


    private void checkUser(int userId) {
        UserApi userApi = RetrofitService.getRetrofit().create(UserApi.class);
        userApi.checkUser(userId)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.body() != null) {
                            Toast.makeText(context, "Найден пользователь: " + response.body().getUserId(), Toast.LENGTH_LONG).show();
                            updateUser(response.body());
                        } else {
                            Toast.makeText(context, "Пользователь не найден", Toast.LENGTH_LONG).show();
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
        UserApi userApi = RetrofitService.getRetrofit().create(UserApi.class);
        userApi.createUser()
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.body() != null) {
                            Toast.makeText(context, "Создан пользователь: " + response.body().getUserId(), Toast.LENGTH_LONG).show();
                            updateUser(response.body());
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
