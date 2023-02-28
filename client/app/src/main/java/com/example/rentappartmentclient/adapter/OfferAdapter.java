package com.example.rentappartmentclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappartmentclient.R;
import com.example.rentappartmentclient.model.Offer;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferHolder> {

    private List<Offer> offerList;

    public OfferAdapter(List<Offer> offerList) {
        this.offerList = offerList;
    }


    @NonNull
    @Override
    public OfferHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_offer_item, parent, false);
        return new OfferHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferHolder holder, int position) {
        Offer offer = offerList.get(position);
        holder.name.setText(offer.getFullDescription());
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }
}
