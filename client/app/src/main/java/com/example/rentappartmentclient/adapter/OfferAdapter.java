package com.example.rentappartmentclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappartmentclient.R;
import com.example.rentappartmentclient.retrofit.UserManager;
import com.example.rentappartmentclient.model.database.Favorite;
import com.example.rentappartmentclient.retrofit.FavoriteListManager;
import com.example.rentappartmentclient.model.database.Offer;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferHolder> {

    private final List<Offer> offerList;
    private View view;
    private Context context;
    private final ItemClickListener itemClickListener;
    private int selectedPosition = -1;

    public OfferAdapter(List<Offer> offerList, ItemClickListener itemClickListener) {
        this.offerList = offerList;
        this.itemClickListener=itemClickListener;
    }


    @NonNull
    @Override
    public OfferHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
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

        String address = offer.getAddress().getAddress();
        if(address.contains(",")) {
            address = address.substring(0, address.indexOf(",") + 1) + "\n" +
                    address.substring(address.indexOf(",") + 2);
        }

        String price = offer.getPrice().intValue() + " ₽/мес.";

        String imageURL = "https:" + offer.getMainImage();

        holder.header.setText(header);
        holder.address.setText(address);
        holder.price.setText(price);
        holder.favorite.setChecked(FavoriteListManager.getInstance().checkIfFavorite(offer));
        Picasso.get()
                .load(imageURL)
                .resize(120, 120)
                .centerCrop()
                .into(holder.image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                itemClickListener.onClick(position, offerList.get(position));
                selectedPosition=position;
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
                favorite.setUser(UserManager.getInstance().getCurrentUser());

                if (holder.favorite.isChecked()) {
                    FavoriteListManager.getInstance().saveFavorite(favorite);
                } else {
                    FavoriteListManager.getInstance().deleteFavorite(favorite);
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
}
