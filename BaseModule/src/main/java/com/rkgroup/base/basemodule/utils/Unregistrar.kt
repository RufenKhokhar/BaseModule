package com.rkgroup.base.basemodule.utils

import android.view.ViewTreeObserver

fun interface Unregistrar {

    /**
     * unregisters the [ViewTreeObserver.OnGlobalLayoutListener] and there by does not provide any more callback to the [KeyboardVisibilityEventListener]
     */
    fun unregister()
}