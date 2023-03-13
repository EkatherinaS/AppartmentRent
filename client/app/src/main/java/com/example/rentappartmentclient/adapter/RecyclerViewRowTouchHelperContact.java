package com.example.rentappartmentclient.adapter;

public interface RecyclerViewRowTouchHelperContact {
    void onRowMoved(int from, int to);
    void onRowSelected(SortHolder sortHolder);
    void onRowClear(SortHolder sortHolder);
}
