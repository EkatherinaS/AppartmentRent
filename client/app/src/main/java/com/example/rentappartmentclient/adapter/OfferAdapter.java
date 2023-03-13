package com.example.rentappartmentclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappartmentclient.R;
import com.example.rentappartmentclient.model.Offer;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferHolder> {

    private List<Offer> offerList;
    private View view;
    ItemClickListener itemClickListener;
    int selectedPosition=-1;

    public OfferAdapter(List<Offer> offerList, ItemClickListener itemClickListener) {
        this.offerList = offerList;
        this.itemClickListener=itemClickListener;
    }


    @NonNull
    @Override
    public OfferHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_offer, parent, false);
        return new OfferHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferHolder holder, int position) {
        Offer offer = offerList.get(position);

        String header;
        int areaValue = offer.getArea().intValue();
        if (areaValue == -1) header = offer.getRoomNumber() + "-комн. " + offer.getType();
        else header = offer.getRoomNumber() + "-комн. " + offer.getType() + ", " + areaValue + "м²";

        String addressValue = offer.getAddress().getAddress();
        String district = addressValue.substring(0, addressValue.indexOf(",") + 1);
        String street = addressValue.substring(addressValue.indexOf(",") + 2, addressValue.indexOf("("));
        String address = district + "\n" + street;

        String price = offer.getPrice().intValue() + " ₽/мес.";

        String imageURL = "https:" + offer.getMainImage();

        Boolean favorite = offer.getFavorite();

        holder.header.setText(header);
        holder.address.setText(address);
        holder.price.setText(price);
        holder.favorite.setChecked(favorite);
        Picasso.get()
                .load(imageURL)
                .resize(110, 110)
                .centerCrop()
                .into(holder.image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                itemClickListener.onClick(position, offerList.get(position));
                selectedPosition=position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }
}
