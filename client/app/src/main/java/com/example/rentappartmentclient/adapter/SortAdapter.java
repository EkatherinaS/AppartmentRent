package com.example.rentappartmentclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappartmentclient.R;
import com.example.rentappartmentclient.model.Filter;

import java.util.Collections;
import java.util.List;

public class SortAdapter  extends RecyclerView.Adapter<SortHolder>
implements RecyclerViewRowTouchHelperContact{

    private List<Filter> filterList;

    public SortAdapter(List<Filter> filterList) {
        this.filterList = filterList;
    }

    @NonNull
    @Override
    public SortHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_sort, parent, false);
        return new SortHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SortHolder holder, int position) {
        Filter filter = filterList.get(position);
        holder.name.setText(filter.getName());
        holder.sort.setEnabled(filter.isSortAscending());
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    @Override
    public void onRowMoved(int from, int to) {
        if(from < to) {
            for(int i = from; i < to; i++) {
                Collections.swap(filterList, i, i+1);
            }
        } else {
            for (int i = from; i> to; i--) {
                Collections.swap(filterList, i, i-1);
            }
        }
        notifyItemMoved(from, to);
    }

    @Override
    public void onRowSelected(SortHolder sortHolder) {}

    @Override
    public void onRowClear(SortHolder sortHolder) {}
}
