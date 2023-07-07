package com.rkgroup.base.basemodule.extensions

import android.Manifest
import android.app.NotificationManager
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.Uri
import android.provider.Settings
import android.provider.Telephony
import android.widget.Toast
import androidx.work.WorkManager
import com.rkgroup.base.basemodule.BaseApplication

fun Context.isInternetConnected(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: Network? = connectivityManager.activeNetwork
    val networkCapabilities: NetworkCapabilities? =
        connectivityManager.getNetworkCapabilities(activeNetwork)
    return networkCapabilities?.let {
        it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    } ?: false
}

// preferences
val Context.preferences get() = (applicationContext as BaseApplication).preferences

// resources
val Context.resourcesManager get() = (applicationContext as BaseApplication).resourcesManager

//Toast
fun Context.showToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
fun Context.showToast(resID: Int) = showToast(getString(resID))

//SMS related functions
fun Context.isDefaultSMSApp(): Boolean {
    val defaultPackage = Telephony.Sms.getDefaultSmsPackage(this@isDefaultSMSApp)
    return defaultPackage == packageName
}

//Permission check
fun Context.permissionGranted(permission: String): Boolean {
    return if (permission == Manifest.permission.WRITE_EXTERNAL_STORAGE && isAndroid11ORAbove()) {
        true
    } else if (permission == Manifest.permission.READ_EXTERNAL_STORAGE && isAndroid13ORAbove()) {
        true
    } else {
        checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }
}

fun Context.permissionGranted(permissions: List<String>) = permissions.all { permissionGranted(it) }


/// notification
val Context.notificationManager: NotificationManager
    get() = getSystemService(NotificationManager::class.java)

// work manager
val Context.workManager: WorkManager
    get() = WorkManager.getInstance(this)


// common utils methods
fun Context.shareTextData(mData: String) {
    try {
        val intentTextData = Intent(Intent.ACTION_SEND)
        intentTextData.type = "text/plain"
        intentTextData.putExtra(Intent.EXTRA_SUBJECT, "Data")
        intentTextData.putExtra(Intent.EXTRA_TEXT, mData)
        startActivity(Intent.createChooser(intentTextData, "Choose to share"))
    } catch (ex: Exception) {
        //  ex.recordException("shareTextData")
    }
}

fun Context.searchData(text: String) {
    try {
        val intentSearch = Intent(Intent.ACTION_WEB_SEARCH)
        intentSearch.putExtra(SearchManager.QUERY, text)
        startActivity(intentSearch)
    } catch (ex: Exception) {
        // ex.recordException("searchData")
    }
}

fun Context.openWebUrl(webUrl: String) {
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(webUrl)))
    } catch (ex: Exception) {
        // ex.recordException("searchData")
    }
}

fun Context.rateUs(packageName: String) {
    try {
        openWebUrl("https://play.google.com/store/apps/details?id=$packageName")
    } catch (ex: Exception) {
        // ex.recordException("rateUs")
    }
}

fun Context.shareApp(appName: String, packageName: String) {
    try {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, appName)
        sendIntent.putExtra(
            Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=${packageName}"
        )
        sendIntent.type = "text/plain"
        startActivity(sendIntent)
    } catch (ex: Exception) {
        // ex.recordException("shareApp")
    }
}

fun Context.openAppInfo(packageName: String) {
    try {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
    } catch (_: Exception) {

    }
}