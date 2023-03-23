package com.example.rentappartmentclient;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.rentappartmentclient.retrofit.DataManager;
import com.example.rentappartmentclient.retrofit.UserManager;
import com.example.rentappartmentclient.model.database.Favorite;
import com.example.rentappartmentclient.retrofit.FavoriteListManager;
import com.example.rentappartmentclient.model.database.Image;
import com.example.rentappartmentclient.model.database.Offer;
import com.example.rentappartmentclient.retrofit.api.OfferApi;
import com.example.rentappartmentclient.retrofit.RetrofitService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferFragment extends Fragment {

    public static Context context;

    private TextView tvOfferHeader;
    private TextView tvPrice;
    private TextView tvAddress;
    private ToggleButton tbFavorite;
    private TextView tvRoomNumberValue;
    private TextView tvKitchenSpaceValue;
    private TextView tvYearValue;
    private TextView tvFloorValue;
    private TextView tvDescription;
    private TextView tvContactsName;
    private TextView tvContactsPhone;
    private LinearLayout llImages;

    private Offer offer;

    public OfferFragment(Offer offer) {
        this.offer = offer;
    }

    private String getContactPhone() {
        return offer.getContact().getPhoneNumber();
    }

    private String getContactName() {
        return offer.getContact().getName();
    }

    private String getDescription() {
        return offer.getFullDescription();
    }

    private String getFloor() {
        return offer.getFloor() + "/" + offer.getAddress().getFloorNumber();
    }

    private String getYear() {
        return offer.getAddress().getYear().toString();
    }

    private String getKitchenSpace() {
        return offer.getKitchenArea().intValue() + "м²";
    }

    private String getHeader() {
        String header;
        int areaValue = offer.getArea().intValue();
        if (areaValue == -1) header = offer.getRoomNumber() + "-комн. " + offer.getType();
        else header = offer.getRoomNumber() + "-комн. " + offer.getType() + ", " + areaValue + "м²";
        return header;
    }

    private String getAddress() {
        String address = offer.getAddress().getAddress();
        if (address.contains(",")) {
            return "Пермь, " + address.substring(0, address.indexOf(",") + 1) + "\n" +
                    address.substring(address.indexOf(",") + 2);
        }
        return address;
    }

    private String getPrice() {
        return offer.getPrice().intValue() + " ₽/мес.";
    }
    private String getRoomNumber() {
        return offer.getRoomNumber().toString();
    }

    private boolean getFavorite() {
        return DataManager.getInstance().getFavoriteListManager().checkIfFavorite(offer);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_offer, container, false);
        context = mainView.getContext();
        initializeViews(mainView);
        setData();
        loadImages();
        MainActivity.setSettingsToolbar(getHeader());
        return mainView;
    }

    private void setData() {
        tvOfferHeader.setText(getHeader());
        tvAddress.setText(getAddress());
        tvPrice.setText(getPrice());
        tvRoomNumberValue.setText(getRoomNumber());
        tvKitchenSpaceValue.setText(getKitchenSpace());
        tvYearValue.setText(getYear());
        tvFloorValue.setText(getFloor());
        tvDescription.setText(getDescription());
        tvContactsName.setText(getContactName());
        tvContactsPhone.setText(getContactPhone());
        tbFavorite.setChecked(getFavorite());
    }

    private void initializeViews(View mainView){
        tvOfferHeader = mainView.findViewById(R.id.tvOfferHeader);
        tvPrice = mainView.findViewById(R.id.tvPrice);
        tvAddress = mainView.findViewById(R.id.tvAddress);
        tvRoomNumberValue = mainView.findViewById(R.id.tvRoomNumberValue);
        tvKitchenSpaceValue = mainView.findViewById(R.id.tvKitchenSpaceValue);
        tvYearValue = mainView.findViewById(R.id.tvYearValue);
        tvFloorValue = mainView.findViewById(R.id.tvFloorValue);
        tvDescription = mainView.findViewById(R.id.tvDescription);
        tvContactsName = mainView.findViewById(R.id.tvContactsName);
        tvContactsPhone = mainView.findViewById(R.id.tvContactsPhone);
        llImages = mainView.findViewById(R.id.llImages);
        tbFavorite = mainView.findViewById(R.id.tbFavorite);

        tbFavorite.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Favorite favorite = new Favorite();
                    favorite.setOffer(offer);
                    favorite.setUser(DataManager.getInstance().getUser());
                    Log.i("OfferFragment", "Favorite changed " + offer.getId() + " to " + tbFavorite.isChecked());

                    if (tbFavorite.isChecked()) {
                        DataManager.getInstance().getFavoriteListManager().saveFavorite(favorite);
                    } else {
                        DataManager.getInstance().getFavoriteListManager().deleteFavorite(favorite);
                    }
                }
            });
    }

    private void loadImages() {
        OfferApi offerApi = RetrofitService.getRetrofit().create(OfferApi.class);
        offerApi.getImagesByOffer(offer.getId())
                .enqueue(new Callback<List<Image>>() {
                    @Override
                    public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                        Log.i("OfferFragment", "Images loaded: onResponse");
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Image>> call, Throwable t) {
                        Log.w("OfferFragment", "Images loaded: onResponse null");
                    }
                });
    }

    private void populateListView(List<Image> imageList) {
        for(Image image:imageList) {
            ImageView imageView = new ImageView (context);
            Picasso.get()
                    .load("https:" + image.getImageUrl())
                    .resize(400, 400)
                    .centerCrop()
                    .into(imageView);
            llImages.addView(imageView);
        }
    }
}