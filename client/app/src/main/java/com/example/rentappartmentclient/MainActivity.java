package com.example.rentappartmentclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.rentappartmentclient.retrofit.DataManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.apache.log4j.Logger;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    private static ImageView sortButton;
    private static Toolbar toolbar;
    private final DataManager dataManager = DataManager.getInstance();


    public static void setHomeToolbar(String title) {
        Log.i("MainActivity", "Home toolbar set: " + title);
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(null);
        sortButton.setVisibility(View.VISIBLE);
    }

    public static void setSettingsToolbar(String title) {
        Log.i("MainActivity", "Settings toolbar set: " + title);
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        sortButton.setVisibility(View.INVISIBLE);
    }

    private void setHomeFragment() {
        Log.i("MainActivity", "Home fragment set");
        getSupportFragmentManager().beginTransaction().replace(R.id.container, dataManager.getHomeFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.home);
        setHomeToolbar(getResources().getString(R.string.home));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.i("MainActivity", "Navigation bar clicked:" + item.getItemId());
        switch (item.getItemId()) {
            case R.id.favorite:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, dataManager.getFavoritesFragment()).commit();
                setHomeToolbar(getResources().getString(R.string.favorites));
                return true;

            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, dataManager.getHomeFragment()).commit();
                setHomeToolbar(getResources().getString(R.string.home));
                return true;

            case R.id.filter:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, dataManager.getFiltersFragment()).commit();
                setSettingsToolbar(getResources().getString(R.string.filters));
                return true;

            case R.id.location:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, dataManager.getLocationFragment()).commit();
                setSettingsToolbar(getResources().getString(R.string.location));
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity", "MainActivity created");
        setContentView(R.layout.activity_main);

        dataManager.createManagers(this);
        
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomNavigationView.getSelectedItemId() == R.id.favorite) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, dataManager.getFavoritesFragment()).commit();
                    setHomeToolbar(getResources().getString(R.string.favorites));
                } else if (bottomNavigationView.getSelectedItemId() == R.id.favorite) {
                    setHomeFragment();
                } else if (bottomNavigationView.getSelectedItemId() == R.id.filter) {
                    setHomeFragment();
                    dataManager.updateOfferListWithOptions();
                } else if (bottomNavigationView.getSelectedItemId() == R.id.location) {
                    setHomeFragment();
                    dataManager.updateLocation();
                } else {
                    setHomeFragment();
                    dataManager.updateOfferListWithOptions();
                }
            }
        });

        sortButton = findViewById(R.id.sort_button);
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MainActivity", "sort button clicked");
                getSupportFragmentManager().beginTransaction().replace(R.id.container, dataManager.getSortFragment()).commit();
                setSettingsToolbar(getResources().getString(R.string.sort));
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }
}