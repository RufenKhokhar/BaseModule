package com.rkgroup.base.basemodule.extensions

import android.view.View
import android.view.ViewGroup

fun ViewGroup.addCleanView(view: View?) {
    (view?.parent as? ViewGroup)?.removeView(view)
    this.removeAllViews()
    this.addView(view)
}