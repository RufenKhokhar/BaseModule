package com.rkgroup.base.basemodule.recyclerview

import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

abstract class RecyclerViewScrollDetector : RecyclerView.OnScrollListener() {
    private var mScrollThreshold = 0
    abstract fun onScrollUp()
    abstract fun onScrollDown()
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val isSignificantDelta = abs(dy) > mScrollThreshold
        if (isSignificantDelta) {
            if (dy > 0) {
                onScrollUp()
            } else {
                onScrollDown()
            }
        }
    }

    fun setScrollThreshold(scrollThreshold: Int) {
        mScrollThreshold = scrollThreshold
    }
}