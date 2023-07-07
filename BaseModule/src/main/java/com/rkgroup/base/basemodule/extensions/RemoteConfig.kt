package com.rkgroup.base.basemodule.extensions

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.rkgroup.base.basemodule.ads.AdsConstants

fun FirebaseRemoteConfig.getBannerAd(key:String):String{
    return if (AdsConstants.testAds) {
        "ca-app-pub-3940256099942544/6300978111"
    }else{
        getString(key).trim()
    }
}
fun FirebaseRemoteConfig.getNativeAd(key:String):String{
    return if (AdsConstants.testAds) {
        "ca-app-pub-3940256099942544/2247696110"
    }else{
        getString(key).trim()
    }
}
fun FirebaseRemoteConfig.getInterstitialAd(key:String):String{
    return if (AdsConstants.testAds) {
        "ca-app-pub-3940256099942544/1033173712"
    }else{
        getString(key).trim()
    }
}
fun FirebaseRemoteConfig.getRewardedAd(key:String):String{
    return if (AdsConstants.testAds) {
        "ca-app-pub-3940256099942544/5224354917"
    }else{
        getString(key).trim()
    }
}
fun FirebaseRemoteConfig.getRewardedInterstitialAd(key:String):String{
    return if (AdsConstants.testAds) {
        "ca-app-pub-3940256099942544/5354046379"
    }else{
        getString(key).trim()
    }
}
fun FirebaseRemoteConfig.getStringValue(key: String) = getString(key).trim()