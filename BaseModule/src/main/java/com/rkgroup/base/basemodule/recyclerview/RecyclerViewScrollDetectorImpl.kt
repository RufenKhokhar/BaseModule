package com.rkgroup.base.basemodule.recyclerview

import androidx.recyclerview.widget.RecyclerView

 class RecyclerViewScrollDetectorImpl : RecyclerViewScrollDetector() {
    private var mScrollDirectionListener: ScrollDirectionListener? = null
    private var mOnScrollListener: RecyclerView.OnScrollListener? = null
    fun setScrollDirectionListener(scrollDirectionListener: ScrollDirectionListener?) {
        mScrollDirectionListener = scrollDirectionListener
    }

    fun setOnScrollListener(onScrollListener: RecyclerView.OnScrollListener?) {
        mOnScrollListener = onScrollListener
    }

    override fun onScrollDown() {
        if (mScrollDirectionListener != null) {
            mScrollDirectionListener!!.onScrollDown()
        }
    }


    override fun onScrollUp() {
        if (mScrollDirectionListener != null) {
            mScrollDirectionListener!!.onScrollUp()
        }
    }


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        mOnScrollListener?.onScrolled(recyclerView, dx, dy)
        super.onScrolled(recyclerView, dx, dy)
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        mOnScrollListener?.onScrollStateChanged(recyclerView, newState)
        super.onScrollStateChanged(recyclerView, newState)
    }
}