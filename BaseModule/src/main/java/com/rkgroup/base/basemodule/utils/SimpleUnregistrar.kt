package com.rkgroup.base.basemodule.utils


import android.app.Activity
import android.os.Build
import android.view.ViewTreeObserver

import java.lang.ref.WeakReference



class SimpleUnregistrar internal constructor(
    activity: Activity,
    globalLayoutListener: ViewTreeObserver.OnGlobalLayoutListener
) : Unregistrar {

    private val activityWeakReference: WeakReference<Activity> = WeakReference(activity)

    private val onGlobalLayoutListenerWeakReference: WeakReference<ViewTreeObserver.OnGlobalLayoutListener> =
        WeakReference(globalLayoutListener)

    override fun unregister() {
        val activity = activityWeakReference.get()
        val globalLayoutListener = onGlobalLayoutListenerWeakReference.get()

        if (null != activity && null != globalLayoutListener) {
            val activityRoot = KeyboardVisibilityEvent.getActivityRoot(activity)
            activityRoot.viewTreeObserver
                .removeOnGlobalLayoutListener(globalLayoutListener)
        }

        activityWeakReference.clear()
        onGlobalLayoutListenerWeakReference.clear()
    }
}