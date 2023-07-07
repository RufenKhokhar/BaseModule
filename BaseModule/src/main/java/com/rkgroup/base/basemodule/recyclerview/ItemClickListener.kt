package com.rkgroup.base.basemodule.recyclerview

interface ItemClickListener<T> {

    fun onItemClick(position:Int,item:T)

}