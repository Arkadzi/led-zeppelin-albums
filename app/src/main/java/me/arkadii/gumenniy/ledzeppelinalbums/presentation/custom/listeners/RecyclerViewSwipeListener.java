package me.arkadii.gumenniy.ledzeppelinalbums.presentation.custom.listeners;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by sebastian on 18.09.16.
 */
public abstract class RecyclerViewSwipeListener extends ItemTouchHelper.SimpleCallback {

    public RecyclerViewSwipeListener() {
        super(0, ItemTouchHelper.RIGHT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }
}
