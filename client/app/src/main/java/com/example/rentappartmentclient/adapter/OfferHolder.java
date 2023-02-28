package com.example.rentappartmentclient.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappartmentclient.R;

public class OfferHolder extends RecyclerView.ViewHolder {

    public TextView name;

    public OfferHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tvName);
    }
}
