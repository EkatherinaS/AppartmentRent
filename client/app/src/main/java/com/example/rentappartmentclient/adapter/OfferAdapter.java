package com.example.rentappartmentclient.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappartmentclient.R;
import com.example.rentappartmentclient.retrofit.DataManager;
import com.example.rentappartmentclient.model.database.Favorite;
import com.example.rentappartmentclient.model.database.Offer;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferHolder> {

    private final List<Offer> offerList;
    private final ItemClickListener itemClickListener;


    public OfferAdapter(List<Offer> offerList, ItemClickListener itemClickListener) {
        this.offerList = offerList;
        this.itemClickListener=itemClickListener;
    }


    @NonNull
    @Override
    public OfferHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_offer, parent, false);
        return new OfferHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull OfferHolder holder, int position) {

        Offer offer = offerList.get(position);

        String header = getHeader(offer);
        String address = getAddress(offer);
        String price = getPrice(offer);
        String imageURL = getImageURL(offer);

        holder.header.setText(header);
        holder.address.setText(address);
        holder.price.setText(price);
        holder.favorite.setChecked(DataManager.getInstance().getFavoriteListManager().checkIfFavorite(offer));
        Picasso.get()
                .load(imageURL)
                .resize(120, 120)
                .centerCrop()
                .into(holder.image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Log.i("OfferAdapter", "itemView clicked on position: " + position);
                itemClickListener.onClick(position, offerList.get(position));
                notifyDataSetChanged();
            }
        });


        holder.favorite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Favorite favorite = new Favorite();
                favorite.setOffer(offerList.get(holder.getAdapterPosition()));
                favorite.setUser(DataManager.getInstance().getUser());
                Log.i("OfferAdapter", "favorite clicked on position: " + holder.getAdapterPosition());

                if (holder.favorite.isChecked()) {
                    DataManager.getInstance().getFavoriteListManager().saveFavorite(favorite);
                } else {
                    DataManager.getInstance().getFavoriteListManager().deleteFavorite(favorite);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (offerList != null)
            return offerList.size();
        return 0;
    }

    private String getHeader(Offer offer) {
        int areaValue = offer.getArea().intValue();
        if (areaValue == -1) return offer.getRoomNumber() + "-комн. " + offer.getType();
        else return offer.getRoomNumber() + "-комн. " + offer.getType() + ", " + areaValue + "м²";
    }

    private String getAddress(Offer offer) {
        String result = offer.getAddress().getAddress();
        if(result.contains(",")) {
            return result.substring(0, result.indexOf(",") + 1) + "\n" +
                    result.substring(result.indexOf(",") + 2);
        }
        return result;
    }

    private String getPrice(Offer offer) {
        return offer.getPrice().intValue() + " ₽/мес.";
    }

    private String getImageURL(Offer offer) {
        return "https:" + offer.getMainImage();
    }
}
