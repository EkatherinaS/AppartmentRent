package com.example.rentappartmentclient.retrofit;

import android.content.Context;
import android.util.Log;

import com.example.rentappartmentclient.FavoritesFragment;
import com.example.rentappartmentclient.FiltersFragment;
import com.example.rentappartmentclient.HomeFragment;
import com.example.rentappartmentclient.LocationFragment;
import com.example.rentappartmentclient.SortFragment;
import com.example.rentappartmentclient.model.OfferFilters;
import com.example.rentappartmentclient.model.database.Filter;
import com.example.rentappartmentclient.model.database.Offer;
import com.example.rentappartmentclient.model.database.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class DataManager implements Observer {

    private List<Offer> offerList;
    private List<Offer> favoriteList;

    private List<Filter> filterList;
    private User user;

    private UserManager userManager;
    private OfferListManager offerListManager;
    private FavoriteListManager favoriteListManager;
    private FilterManager filterListManager;

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
    public List<Filter> getFilterList() {
        return filterList;
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

    public FilterManager getFilterListManager() {
        return filterListManager;
    }

    public FavoritesFragment getFavoritesFragment() {
        if (favoritesFragment == null) {
            favoritesFragment = new FavoritesFragment();
            Log.i("DataManager", "FavoritesFragment created");
        }
        return favoritesFragment;
    }
    public FiltersFragment getFiltersFragment() {
        if (filtersFragment == null) {
            filtersFragment = new FiltersFragment();
            Log.i("DataManager", "FiltersFragment created");
        }
        return filtersFragment;
    }
    public HomeFragment getHomeFragment() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            Log.i("DataManager", "HomeFragment created");
        }
        return homeFragment;
    }
    public LocationFragment getLocationFragment() {
        if (locationFragment == null) {
            locationFragment = new LocationFragment();
            Log.i("DataManager", "LocationFragment created");
        }
        return locationFragment;
    }
    public SortFragment getSortFragment() {
        if (sortFragment == null) {
            sortFragment = new SortFragment();
            Log.i("DataManager", "SortFragment created");
        }
        return sortFragment;
    }


    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
            Log.i("DataManager", "DataManager created");
        }
        return instance;
    }

    private DataManager() {
        user = new User();
        offerList = new ArrayList<>();
        favoriteList = new ArrayList<>();
    }

    public void createManagers(Context context) {
        this.context = context;

        filterListManager = new FilterManager(context);
        filterListManager.addObserver(this);
        Log.i("DataManager", "FilterManager created");
        filterListManager.loadFilterList();

        userManager = new UserManager(context);
        userManager.addObserver(this);
        Log.i("DataManager", "UserManager created");
        userManager.loadUser();
    }


    @Override
    public void update(Observable o, Object arg) {
        if (userManager.equals(o)) {
            onUserUpdate();
        } else if (filterListManager.equals(o)) {
            onFilterListUpdate();
        } else if (favoriteListManager.equals(o)) {
            onFavoriteListUpdate();
        } else if (offerListManager.equals(o)) {
            onOfferListUpdate();
        }
    }

    private void onFilterListUpdate() {
        Log.i("DataManager", "Observable changed: filterListManager");
        filterList = filterListManager.getFilterList();
    }

    private void onUserUpdate() {
        Log.i("DataManager", "Observable changed: userManager");
        user = userManager.getCurrentUser();

        favoriteListManager = new FavoriteListManager(user);
        favoriteListManager.addObserver(this);
        Log.i("DataManager", "FavoriteListManager created");
        favoriteListManager.loadFavoriteList();

        offerListManager = new OfferListManager(context);
        offerListManager.addObserver(this);
        Log.i("DataManager", "OfferListManager created");
        offerListManager.loadOffers();
    }
    private void onOfferListUpdate() {
        Log.i("DataManager", "Observable changed: offerListManager");
        offerList = offerListManager.getOfferList();

        getHomeFragment().populateListView(offerList);

        OfferFilters.setValues();
    }
    private void onFavoriteListUpdate() {
        Log.i("DataManager", "Observable changed: favoriteListManager");
        favoriteList = favoriteListManager.getFavoriteList();
        if (favoritesFragment != null) {
            favoritesFragment.populateListView(favoriteList);
        }
    }


    public void updateOfferListWithOptions() {
        if (sortFragment != null && filtersFragment != null) {
            updateFilterSort();
        } else if (sortFragment != null) {
            updateSort();
        } else if (filtersFragment != null) {
            updateFilter();
        }
    }


    private void updateSort() {
        Log.i("DataManager", "Offer list update: updateSort");
        offerListManager.loadSortedOffers(sortFragment.getFilterList());
    }

    private void updateFilter() {
        Log.i("DataManager", "Offer list update: updateFilter");
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

    private void updateFilterSort() {
        Log.i("DataManager", "Offer list update: updateFilterSort");
        offerListManager.loadSortedFilteredOffers(filtersFragment.getTypeFlat(), filtersFragment.getTypeRoom(),
                filtersFragment.getPriceMin(), filtersFragment.getPriceMax(),
                filtersFragment.getTypeStudio(),
                filtersFragment.getRoomNumberMin(), filtersFragment.getRoomNumberMax(),
                filtersFragment.getSpaceMin(), filtersFragment.getSpaceMax(),
                filtersFragment.getKitchenSpaceMin(), filtersFragment.getKitchenSpaceMax(),
                filtersFragment.getYearMin(), filtersFragment.getYearMax(),
                filtersFragment.getFloorMin(), filtersFragment.getFloorMax(),
                filtersFragment.getFloorNumberMin(), filtersFragment.getFloorNumberMax(),
                sortFragment.getFilterList());
    }

    public void updateLocation() {}
}
