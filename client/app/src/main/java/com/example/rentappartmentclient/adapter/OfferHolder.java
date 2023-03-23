package com.example.rentappartmentclient.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappartmentclient.R;

public class OfferHolder extends RecyclerView.ViewHolder {

    public TextView header;
    public TextView address;
    public TextView price;
    public ToggleButton favorite;
    public ImageView image;

    public OfferHolder(@NonNull View itemView) {
        super(itemView);
        header = itemView.findViewById(R.id.tvOfferHeader);
        address = itemView.findViewById(R.id.tvAddress);
        price = itemView.findViewById(R.id.tvPrice);
        favorite = itemView.findViewById(R.id.tbFavorite);
        image = itemView.findViewById(R.id.ivOffer);
    }
}
