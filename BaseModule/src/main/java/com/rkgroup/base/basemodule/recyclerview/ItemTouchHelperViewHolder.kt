package com.rkgroup.base.basemodule.recyclerview

interface ItemTouchHelperViewHolder {
    /**
     * Called when the [androidx.recyclerview.widget.ItemTouchHelper] first registers an item as being moved or swiped.
     * Implementations should update the item view to indicate it's active state.
     */
    fun onItemSelected()

    /**
     * Called when the [androidx.recyclerview.widget.ItemTouchHelper] has completed the move or swipe, and the active item
     * state should be cleared.
     */
    fun onItemClear()
}