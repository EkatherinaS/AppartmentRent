package com.example.rentappartmentclient.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerRowMoveCallback extends ItemTouchHelper.Callback{

    public RecyclerViewRowTouchHelperContact touchHelperContact;

    public RecyclerRowMoveCallback(RecyclerViewRowTouchHelperContact touchHelperContact) {
        this.touchHelperContact = touchHelperContact;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(dragFlag, 0);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        this.touchHelperContact.onRowMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        Log.i("RecyclerRowMoveCallback",
                "moved from " + viewHolder.getAdapterPosition() + " to " + target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if(actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if(viewHolder instanceof SortHolder) {
                SortHolder sortHolder = (SortHolder) viewHolder;
                touchHelperContact.onRowSelected(sortHolder);
            }
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if(viewHolder instanceof SortHolder) {
            SortHolder sortHolder = (SortHolder) viewHolder;
            touchHelperContact.onRowSelected(sortHolder);
        }
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {}
}
