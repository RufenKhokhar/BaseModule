package com.rkgroup.base.basemodule.extensions

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast


@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
fun isAndroid10ORAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
fun isAndroid13ORAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
fun isAndroid8ORAbove() = Build.VERSION.SDK_INT>=Build.VERSION_CODES.O
@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
fun isAndroid11ORAbove() = Build.VERSION.SDK_INT>=Build.VERSION_CODES.R