package com.example.rentappartmentclient.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappartmentclient.R;

public class SortHolder  extends RecyclerView.ViewHolder{

    public Button name;
    public ToggleButton sort;

    public SortHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.btnFilter);
        sort = itemView.findViewById(R.id.tbSort);
    }

}
