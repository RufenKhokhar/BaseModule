package com.rkgroup.base.basemodule.extensions

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.rkgroup.base.basemodule.recyclerview.OnStartDragListener
import com.rkgroup.base.basemodule.recyclerview.RecyclerBaseAdapter
import com.rkgroup.base.basemodule.recyclerview.RecyclerViewScrollDetectorImpl
import com.rkgroup.base.basemodule.recyclerview.ScrollDirectionListener
import com.rkgroup.base.basemodule.recyclerview.SimpleItemTouchHelperCallback

fun RecyclerView.setItemDragging(enable: Boolean) {
    if (adapter !is RecyclerBaseAdapter<*, *>) {
        throw IllegalArgumentException("To use item dragging feature, you need to extends your adapter with RecyclerBaseAdapter")
    }
    val recyclerBaseAdapter = adapter as RecyclerBaseAdapter<*, *>
    val callback: ItemTouchHelper.Callback = SimpleItemTouchHelperCallback(recyclerBaseAdapter)
    val mItemTouchHelper = ItemTouchHelper(callback)
    mItemTouchHelper.attachToRecyclerView(this)
    recyclerBaseAdapter.dragListener = if (enable) object : OnStartDragListener {
        override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
            mItemTouchHelper.startDrag(viewHolder)
        }
    } else null
}

fun RecyclerView.attachScrollDirectionListener(scrollDirectionListener: ScrollDirectionListener) {
    val scrollDetector = RecyclerViewScrollDetectorImpl()
    scrollDetector.setScrollDirectionListener(scrollDirectionListener)
    scrollDetector.setScrollThreshold(4.dp)
    addOnScrollListener(scrollDetector)
}