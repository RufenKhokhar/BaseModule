package com.rkgroup.base.basemodule.ads

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.rkgroup.base.basemodule.R
import com.rkgroup.base.basemodule.extensions.isInternetConnected
import com.rkgroup.base.basemodule.extensions.visible

object BannerAd {


    fun load(adContainer: ViewGroup, adId: String, makeCollapsable: Boolean = true) =
        adContainer.post {
            if (adContainer.context.isInternetConnected().not()) {
                return@post
            }
            val placeholder = LayoutInflater.from(adContainer.context)
                .inflate(R.layout.banner_ad_laceholder, null, false)
            adContainer.visible()
            adContainer.removeAllViews()
            adContainer.addView(placeholder)
            val adView = AdView(adContainer.context)
            adView.adUnitId = adId
            adView.setAdSize(getAdaptiveAdSize(adView.context))
            adView.adListener = object : AdListener() {
                override fun onAdLoaded() {
                    adContainer.removeAllViews()
                    adContainer.addView(adView)
                    val mContext: Activity = adContainer.context as Activity
                    Firebase.analytics.logEvent("show_banner_ad", Bundle().apply {
                        putString("screen", mContext.localClassName)
                    })
                }

                override fun onAdFailedToLoad(p0: LoadAdError) {
                    adContainer.removeAllViews()
                }
            }
            val adRequest = AdRequest.Builder()
            if (makeCollapsable) {
                adRequest.addNetworkExtrasBundle(AdMobAdapter::class.java, Bundle().apply {
                    putString("collapsible", "bottom")
                })
            }
            adView.loadAd(adRequest.build())
        }


    private fun getAdaptiveAdSize(mContext:Context): AdSize {
        val display = Resources.getSystem().displayMetrics
        val adSize = display.widthPixels / display.density
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
            mContext,
            adSize.toInt()
        )
    }

}