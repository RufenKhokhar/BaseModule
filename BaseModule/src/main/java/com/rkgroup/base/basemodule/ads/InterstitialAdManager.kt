package com.rkgroup.base.basemodule.ads

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.rkgroup.base.basemodule.extensions.isInternetConnected


object InterstitialAdManager {
    private var interstitialAdRequesting = false
    private var interstitialAd: InterstitialAd? = null
    val adLoaded get() = interstitialAd != null

    fun restInterstitialAdCap(capSec: Long = 10000) {
        AdsConstants.interstitialAdCapComplete = false
        Handler(Looper.myLooper()!!).postDelayed({
            AdsConstants.interstitialAdCapComplete = true
        }, capSec)
    }


    fun loadInterstitialAd(
        mContext: Context,
        adUnitId: String,
        interstitialAdLoadCallback: InterstitialAdLoadCallback? = null
    ) {
        if (interstitialAdRequesting ||
            mContext.isInternetConnected().not() ||
            interstitialAd != null
        ) return
        val adRequests = AdRequest.Builder().build()
        interstitialAdRequesting = true
        AdsConstants.interstitialAdLoaded = false
        InterstitialAd.load(mContext, adUnitId, adRequests,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    AdsConstants.interstitialAdLoaded = false
                    interstitialAd = null
                    interstitialAdRequesting = false
                    interstitialAdLoadCallback?.onAdFailedToLoad(p0)
                }

                override fun onAdLoaded(p0: InterstitialAd) {
                    interstitialAd = p0
                    AdsConstants.interstitialAdLoaded = true
                    interstitialAdRequesting = false
                    interstitialAdLoadCallback?.onAdLoaded(p0)

                }
            })
    }

    fun showInterstitialAd(activity: Activity, callback: FullScreenContentCallback? = null) {
        if (AdsConstants.showingAppOpenAd) {
            callback?.onAdFailedToShowFullScreenContent(AdError(0, "", ""))
            return
        }
        interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdClicked() {
                callback?.onAdClicked()
            }

            override fun onAdDismissedFullScreenContent() {
                restInterstitialAdCap()
                AdsConstants.showingInterAd = false
                callback?.onAdDismissedFullScreenContent()
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                AdsConstants.showingInterAd = false
                callback?.onAdFailedToShowFullScreenContent(p0)
            }

            override fun onAdImpression() {
                callback?.onAdImpression()
            }

            override fun onAdShowedFullScreenContent() {
                AdsConstants.showingInterAd = true
                interstitialAd = null
                callback?.onAdShowedFullScreenContent()
                Firebase.analytics.logEvent("show_interstitial_ad", Bundle().apply {
                    putString("screen", activity.localClassName)
                })

            }
        }
        interstitialAd?.show(activity) ?: run {
            callback?.onAdFailedToShowFullScreenContent(AdError(0, "", ""))
        }
    }


}
