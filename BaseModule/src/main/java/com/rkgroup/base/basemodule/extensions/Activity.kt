package com.rkgroup.base.basemodule.extensions

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.FragmentContainerView

fun Activity.hideSystemUI(fcvContainerMain: FragmentContainerView) {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, fcvContainerMain).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

@Suppress("DEPRECATION")
fun Activity.showSystemUI() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        val controller = window.insetsController
        controller?.show(WindowInsets.Type.systemBars())
    } else {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_VISIBLE)
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}