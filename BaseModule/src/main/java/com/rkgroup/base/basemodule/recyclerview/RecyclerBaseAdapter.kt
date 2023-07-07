package com.rkgroup.base.basemodule.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Collections
import kotlin.coroutines.CoroutineContext


abstract class RecyclerBaseAdapter<VB : ViewBinding, T>(private val itemInflater: (LayoutInflater, ViewGroup, Boolean) -> VB) :
    RecyclerView.Adapter<RecyclerBaseAdapter.BaseView<VB>>(), CoroutineScope,
    ItemTouchHelperAdapter {

    var dragListener: OnStartDragListener? = null

    protected val recyclerViewItems = ArrayList<T>()

    open class BaseView<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseView<VB> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BaseView(itemInflater.invoke(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int = recyclerViewItems.size

    fun getItemAt(position: Int) = recyclerViewItems[position]
    open fun addItemAt(position: Int, item: T) {
        recyclerViewItems[position] = item
        notifyItemInserted(position)
    }

    open fun addItem(item: T) {
        recyclerViewItems.add(item)
        notifyItemInserted(itemCount - 1)
    }
    open fun containsItem(item: T) =recyclerViewItems.contains(item)

    open fun findItemPosition(item: T) = recyclerViewItems.indexOf(item)

    open fun addItems(items: List<T>) = launch {
        val adapterUtils = AdapterUtils(recyclerViewItems, items)
        val result = DiffUtil.calculateDiff(adapterUtils)
        recyclerViewItems.addAll(items)
        result.dispatchUpdatesTo(this@RecyclerBaseAdapter)
    }
    open fun setItems(items: List<T>) = launch {
        val adapterUtils = AdapterUtils(recyclerViewItems, items)
        val result = DiffUtil.calculateDiff(adapterUtils)
        recyclerViewItems.clear()
        recyclerViewItems.addAll(items)
        result.dispatchUpdatesTo(this@RecyclerBaseAdapter)
    }

    open fun removeItemAt(position: Int) {
        recyclerViewItems.removeAt(position)
        notifyItemRemoved(position)
    }

    open fun removeItem(item: T) = launch {
        val position = recyclerViewItems.indexOf(item)
        removeItemAt(position)
    }


    @SuppressLint("ClickableViewAccessibility")
    @CallSuper
    override fun onBindViewHolder(holder: BaseView<VB>, position: Int) {
        holder.itemView.setOnLongClickListener {
            holder.itemView.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    dragListener?.onStartDrag(holder)
                } else {
                    holder.itemView.setOnTouchListener(null)
                }
                return@setOnTouchListener true
            }
            return@setOnLongClickListener true
        }

    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(recyclerViewItems, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        recyclerViewItems.removeAt(position)
        notifyItemRemoved(position)
    }
}