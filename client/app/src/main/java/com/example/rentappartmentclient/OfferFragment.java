package com.example.rentappartmentclient;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.rentappartmentclient.adapter.ItemClickListener;
import com.example.rentappartmentclient.adapter.OfferAdapter;
import com.example.rentappartmentclient.model.Image;
import com.example.rentappartmentclient.model.Offer;
import com.example.rentappartmentclient.retrofit.OfferApi;
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
        return offer.getKitchenArea().intValue() + "????";
    }

    private String getHeader() {
        String header;
        int areaValue = offer.getArea().intValue();
        if (areaValue == -1) header = offer.getRoomNumber() + "-????????. " + offer.getType();
        else header = offer.getRoomNumber() + "-????????. " + offer.getType() + ", " + areaValue + "????";
        return header;
    }

    private String getAddress() {
        String address;
        String addressValue = offer.getAddress().getAddress();
        String district = addressValue.substring(0, addressValue.indexOf(",") + 1);
        String street = addressValue.substring(addressValue.indexOf(",") + 2, addressValue.indexOf("("));
        address = "??????????, " + district + "\n" + street;
        return address;
    }

    private String getPrice() {
        return offer.getPrice().intValue() + " ???/??????.";
    }
    private String getRoomNumber() {
        return offer.getRoomNumber().toString();
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
        tbFavorite.setChecked(offer.getFavorite());
        tvRoomNumberValue.setText(getRoomNumber());
        tvKitchenSpaceValue.setText(getKitchenSpace());
        tvYearValue.setText(getYear());
        tvFloorValue.setText(getFloor());
        tvDescription.setText(getDescription());
        tvContactsName.setText(getContactName());
        tvContactsPhone.setText(getContactPhone());
    }

    private void initializeViews(View mainView){
        tvOfferHeader = mainView.findViewById(R.id.tvOfferHeader);
        tvPrice = mainView.findViewById(R.id.tvPrice);
        tvAddress = mainView.findViewById(R.id.tvAddress);
        tbFavorite = mainView.findViewById(R.id.tbFavorite);
        tvRoomNumberValue = mainView.findViewById(R.id.tvRoomNumberValue);
        tvKitchenSpaceValue = mainView.findViewById(R.id.tvKitchenSpaceValue);
        tvYearValue = mainView.findViewById(R.id.tvYearValue);
        tvFloorValue = mainView.findViewById(R.id.tvFloorValue);
        tvDescription = mainView.findViewById(R.id.tvDescription);
        tvContactsName = mainView.findViewById(R.id.tvContactsName);
        tvContactsPhone = mainView.findViewById(R.id.tvContactsPhone);
        llImages = mainView.findViewById(R.id.llImages);
    }

    private void loadImages() {
        RetrofitService retrofitService = new RetrofitService();
        OfferApi offerApi = retrofitService.getRetrofit().create(OfferApi.class);
        offerApi.getImagesByOffer(offer.getId())
                .enqueue(new Callback<List<Image>>() {
                    @Override
                    public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Image>> call, Throwable t) {
                        Toast.makeText(context, "Loading images failed", Toast.LENGTH_LONG).show();
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