package com.example.rentappartmentclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    ImageView sortButton;
    Toolbar toolbar;

    FavoritesFragment favoritesFragment = new FavoritesFragment();
    FiltersFragment filtersFragment = new FiltersFragment();
    HomeFragment homeFragment = new HomeFragment();
    LocationFragment locationFragment = new LocationFragment();
    SortFragment sortFragment = new SortFragment();

    private void setHomeToolbar() {
        toolbar.setTitle(R.string.home);
        toolbar.setNavigationIcon(null);
        sortButton.setVisibility(View.VISIBLE);
    }

    private void setSettingsToolbar(int title) {
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.ic_back_foreground);
        //toolbar.setContentInsetStartWithNavigation(0);
        //toolbar.setTitleMarginStart(0);
        sortButton.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                setHomeToolbar();
            }
        });

        sortButton = findViewById(R.id.sort_button);
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, sortFragment).commit();
                setSettingsToolbar(R.string.sort);
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.favorite:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, favoritesFragment).commit();
                setSettingsToolbar(R.string.favorites);
                return true;

            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                setHomeToolbar();
                return true;

            case R.id.filter:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, filtersFragment).commit();
                setSettingsToolbar(R.string.filters);
                return true;

            case R.id.location:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, locationFragment).commit();
                setSettingsToolbar(R.string.location);
                return true;
        }
        return false;
    }
}