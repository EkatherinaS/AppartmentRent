package com.example.rentappartmentclient.retrofit;

import android.content.Context;
import android.widget.Filter;
import android.widget.Toast;

import com.example.rentappartmentclient.FavoritesFragment;
import com.example.rentappartmentclient.FiltersFragment;
import com.example.rentappartmentclient.HomeFragment;
import com.example.rentappartmentclient.LocationFragment;
import com.example.rentappartmentclient.SortFragment;
import com.example.rentappartmentclient.model.OfferFilters;
import com.example.rentappartmentclient.model.database.Offer;
import com.example.rentappartmentclient.model.database.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class DataManager implements Observer {

    private List<Offer> offerList;
    private List<Offer> favoriteList;
    private User user;

    private UserManager userManager;
    private OfferListManager offerListManager;
    private FavoriteListManager favoriteListManager;

    private Context context;
    private static DataManager instance;

    private FavoritesFragment favoritesFragment ;
    private FiltersFragment filtersFragment;
    private HomeFragment homeFragment;

    private LocationFragment locationFragment;
    private SortFragment sortFragment;


    public List<Offer> getOfferList() {
        return offerList;
    }

    public List<Offer> getFavoriteList() {
        return favoriteList;
    }

    public User getUser() {
        return user;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public OfferListManager getOfferListManager() {
        return offerListManager;
    }
    public FavoriteListManager getFavoriteListManager() {
        return favoriteListManager;
    }


    public FavoritesFragment getFavoritesFragment() {
        return favoritesFragment;
    }
    public FiltersFragment getFiltersFragment() {
        return filtersFragment;
    }
    public HomeFragment getHomeFragment() {
        return homeFragment;
    }
    public LocationFragment getLocationFragment() {
        return locationFragment;
    }
    public SortFragment getSortFragment() {
        return sortFragment;
    }


    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    private DataManager() {
        user = new User();
        offerList = new ArrayList<>();
        favoriteList = new ArrayList<>();

        favoritesFragment = new FavoritesFragment();
        filtersFragment = new FiltersFragment();
        homeFragment = new HomeFragment();
        locationFragment = new LocationFragment();
        sortFragment = new SortFragment();
    }

    public void createManagers(Context context) {
        this.context = context;

        userManager = new UserManager(context);
        userManager.addObserver(this);
        userManager.loadUser();
    }


    @Override
    public void update(Observable o, Object arg) {
        if (userManager.equals(o)) {
            onUserUpdate();
        } else if (offerListManager.equals(o)) {
            onOfferListUpdate();
        } else if (favoriteListManager.equals(o)) {
            onFavoriteListUpdate();
        }
    }

    private void onUserUpdate() {
        Toast.makeText(context, "onUserUpdate", Toast.LENGTH_LONG).show();
        user = userManager.getCurrentUser();

        favoriteListManager = new FavoriteListManager(context, user);
        favoriteListManager.addObserver(this);
        favoriteListManager.loadFavoriteList();

        offerListManager = new OfferListManager(context);
        offerListManager.addObserver(this);
        offerListManager.loadOffers();
    }

    private void onOfferListUpdate() {
        Toast.makeText(context, "onOfferListUpdate", Toast.LENGTH_LONG).show();
        offerList = offerListManager.getOfferList();

        homeFragment.populateListView(offerList);

        OfferFilters.setValues();
        filtersFragment.setValues();
        filtersFragment.setListeners();
    }

    private void onFavoriteListUpdate() {
        Toast.makeText(context, "onFavoriteListUpdate", Toast.LENGTH_LONG).show();
        favoriteList = favoriteListManager.getFavoriteList();
        favoritesFragment.populateListView(favoriteList);
    }

    public void updateFilters() {
        offerListManager.loadFilteredOffers(filtersFragment.getTypeFlat(), filtersFragment.getTypeRoom(),
                filtersFragment.getPriceMin(), filtersFragment.getPriceMax(),
                filtersFragment.getTypeStudio(),
                filtersFragment.getRoomNumberMin(), filtersFragment.getRoomNumberMax(),
                filtersFragment.getSpaceMin(), filtersFragment.getSpaceMax(),
                filtersFragment.getKitchenSpaceMin(), filtersFragment.getKitchenSpaceMax(),
                filtersFragment.getYearMin(), filtersFragment.getYearMax(),
                filtersFragment.getFloorMin(), filtersFragment.getFloorMax(),
                filtersFragment.getFloorNumberMin(), filtersFragment.getFloorNumberMax());
    }

    public void updateSort() {
    }

    public void updateLocation() {
    }
}
