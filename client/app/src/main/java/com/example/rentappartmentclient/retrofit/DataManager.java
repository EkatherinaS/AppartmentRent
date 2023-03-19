package com.example.rentappartmentclient.retrofit;

import android.content.Context;
import android.widget.Toast;

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
        }
        return favoritesFragment;
    }
    public FiltersFragment getFiltersFragment() {
        if (filtersFragment == null) {
            filtersFragment = new FiltersFragment();
        }
        return filtersFragment;
    }
    public HomeFragment getHomeFragment() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }
    public LocationFragment getLocationFragment() {
        if (locationFragment == null) {
            locationFragment = new LocationFragment();
        }
        return locationFragment;
    }
    public SortFragment getSortFragment() {
        if (sortFragment == null) {
            sortFragment = new SortFragment();
        }
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
    }

    public void createManagers(Context context) {
        this.context = context;

        filterListManager = new FilterManager(context);
        filterListManager.addObserver(this);
        filterListManager.loadFilterList();

        userManager = new UserManager(context);
        userManager.addObserver(this);
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
        Toast.makeText(context, "onFilterListUpdate", Toast.LENGTH_LONG).show();
        filterList = filterListManager.getFilterList();
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

        getHomeFragment().populateListView(offerList);

        OfferFilters.setValues();
        if (filtersFragment != null) {
            filtersFragment.setValues();
            filtersFragment.setListeners();
        }
    }
    private void onFavoriteListUpdate() {
        Toast.makeText(context, "onFavoriteListUpdate", Toast.LENGTH_LONG).show();
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
        offerListManager.loadSortedOffers(sortFragment.getFilterList());
    }

    private void updateFilter() {
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
    public void updateLocation() {
    }
}
