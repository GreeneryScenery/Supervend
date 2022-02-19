package com.example.supervend.swipeToDismiss

import android.content.ContentValues.TAG
import android.content.SharedPreferences
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.supervend.CartAdapter

class SwipeHelperCallback(val adapter: CartAdapter, private var sp: SharedPreferences, private var itemNames: Array<String>) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(
            0,
            swipeFlags
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        source: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == 16) {
            adapter.onItemRemove(viewHolder.adapterPosition, sp, itemNames)
        }
        else if (direction == 32) {
            adapter.onItemAdd(viewHolder.adapterPosition, sp, itemNames)
        }
    }
}