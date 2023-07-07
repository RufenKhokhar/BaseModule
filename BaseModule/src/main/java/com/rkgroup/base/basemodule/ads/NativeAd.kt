package com.rkgroup.base.basemodule.ads

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.VideoOptions
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.rkgroup.base.basemodule.R
import com.rkgroup.base.basemodule.extensions.gone
import com.rkgroup.base.basemodule.extensions.isInternetConnected
import com.rkgroup.base.basemodule.extensions.visible

object NativeAd {


    private fun load(
        adContainer: ViewGroup, adId: String, adType: String, btnColor: Int, makeBtnRound: Boolean
    ) {
        if (adContainer.context.isInternetConnected().not()) {
            return
        }
        val placeholder = getPlaceHolderByType(adType)
        adContainer.visible()
        adContainer.removeAllViews()
        val placeholderView =
            LayoutInflater.from(adContainer.context).inflate(placeholder, null, false)
        adContainer.addView(placeholderView)
        val adLoader = AdLoader.Builder(adContainer.context, adId).forNativeAd {
            populateNativeAd(adContainer, adType, makeBtnRound, btnColor, it)
        }.withAdListener(object : AdListener() {
            override fun onAdFailedToLoad(p0: LoadAdError) {
                adContainer.gone()
            }
        }).withNativeAdOptions(
            NativeAdOptions.Builder()
                .setMediaAspectRatio(NativeAdOptions.NATIVE_MEDIA_ASPECT_RATIO_LANDSCAPE)
                .setVideoOptions(
                    VideoOptions.Builder().setStartMuted(true).setClickToExpandRequested(true)
                        .build()
                ).build()
        ).build()
        val adRequest = AdRequest.Builder().build()
        adLoader.loadAd(adRequest)
    }

    private fun populateNativeAd(
        adContainer: ViewGroup,
        adType: String,
        makeBtnRound: Boolean,
        btnColor: Int,
        nativeAd: NativeAd
    ) {
        val adView = getAdViewByType(adType)
        adContainer.removeAllViews()
        val nativeView = LayoutInflater.from(adContainer.context).inflate(adView, null, false)
        val actionBtn: Button = nativeView.findViewById(R.id.ad_call_to_action)
        val btnBackground = if (makeBtnRound) R.drawable.bg_ad_call_action_round
        else R.drawable.bg_ad_call_action_normal
        actionBtn.setBackgroundResource(btnBackground)
        if (btnColor != -1) {
            actionBtn.background.setTint(btnColor)
        }
        val mContext: Activity = adContainer.context as Activity
        Firebase.analytics.logEvent("show_native_ad", Bundle().apply {
            putString("screen", mContext.localClassName)
        })
        adContainer.addView(nativeView)
        populateNativeAdView(nativeAd, nativeView as NativeAdView)
    }

    fun populateNativeAd(adContainer: ViewGroup, adType: String, makeBtnRound: Boolean, btnColor: String, nativeAd: NativeAd) {
        val color = try {
            Color.parseColor(btnColor)
        } catch (_: Exception) {
            -1
        }
        populateNativeAd(adContainer, adType, makeBtnRound, color, nativeAd)
    }

    fun load(adContainer: ViewGroup, adId: String, adType: String, btnColor: String, makeBtnRound: Boolean) {
        val color = try {
            Color.parseColor(btnColor)
        } catch (_: Exception) {
            -1
        }
        load(adContainer, adId, adType, color, makeBtnRound)
    }


    fun getPlaceHolderByType(adType: String): Int {
        return when (adType) {
            "1a" -> R.layout.native_1a_placeholder
            "1b" -> R.layout.native_1b_placeholder
            "6a" -> R.layout.native_6a_placeholder
            "6b" -> R.layout.native_6b_placeholder
            "7a" -> R.layout.native_7a_placeholder
            else -> R.layout.native_7b_placeholder

        }
    }

    fun getAdViewByType(adType: String): Int {
        return when (adType) {
            "1a" -> R.layout.native_1a
            "1b" -> R.layout.native_1b
            "6a" -> R.layout.native_6a
            "6b" -> R.layout.native_6b
            "7a" -> R.layout.native_7a
            else -> R.layout.native_7b

        }
    }


    fun populateNativeAdView(nativeAd: NativeAd, adView: NativeAdView) {
        // Set the media view. Media content will be automatically populated in the media view once
        // Set other ad assets
        adView.headlineView = adView.findViewById(R.id.ad_headline)
        val mediaView: MediaView? = adView.findViewById(R.id.ad_media)
        mediaView?.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
        adView.mediaView = mediaView
        adView.bodyView = adView.findViewById(R.id.ad_body)
        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
        adView.iconView = adView.findViewById(R.id.ad_app_icon)/* adView.priceView = adView.findViewById(R.id.ad_price)
         adView.starRatingView = adView.findViewById(R.id.ad_stars)
         adView.storeView = adView.findViewById(R.id.ad_store)
         adView.advertiserView = adView.findViewById(R.id.ad_advertiser)*/

        // The headline is guaranteed to be in every NativeAd.
        if (nativeAd.headline == null && adView.headlineView != null) {
            adView.headlineView!!.visibility = View.INVISIBLE
        } else if (adView.headlineView != null) {
            adView.headlineView!!.visibility = View.VISIBLE
            (adView.headlineView as TextView).text = nativeAd.headline
            adView.headlineView!!.isSelected = true
        }

        // These assets aren't guaranteed to be in every NativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.body == null && adView.bodyView != null) {
            adView.bodyView!!.visibility = View.INVISIBLE
        } else if (adView.bodyView != null) {
            adView.bodyView!!.visibility = View.VISIBLE
            (adView.bodyView as TextView).text = nativeAd.body
            adView.bodyView!!.isSelected = true
        }
        if (nativeAd.callToAction == null && adView.callToActionView != null) {
            adView.callToActionView!!.visibility = View.INVISIBLE
        } else if (adView.callToActionView != null) {
            adView.callToActionView!!.visibility = View.VISIBLE
            (adView.callToActionView as Button).text = nativeAd.callToAction
        }
        if (nativeAd.icon == null && adView.iconView != null) {
            if (nativeAd.images.isNotEmpty()) {
                adView.iconView!!.visibility = View.VISIBLE
                (adView.iconView as ImageView).setImageDrawable(nativeAd.images[0]!!.drawable)
            } else {
                adView.iconView!!.visibility = View.GONE
            }
        } else if (adView.iconView != null) {
            adView.iconView!!.visibility = View.VISIBLE
            (adView.iconView as ImageView).setImageDrawable(nativeAd.icon!!.drawable)
        }
        if (nativeAd.price == null && adView.priceView != null) {
            adView.priceView!!.visibility = View.INVISIBLE
        } else if (adView.priceView != null) {
            adView.priceView!!.visibility = View.VISIBLE
            (adView.priceView as TextView).text = nativeAd.price
        }
        if (nativeAd.store == null && adView.storeView != null) {
            adView.storeView!!.visibility = View.INVISIBLE
        } else if (adView.storeView != null) {
            adView.storeView!!.visibility = View.VISIBLE
            (adView.storeView as TextView).text = nativeAd.store
        }
        if (nativeAd.starRating == null && adView.starRatingView != null) {
            adView.starRatingView!!.visibility = View.INVISIBLE
        } else if (adView.starRatingView != null) {
            (adView.starRatingView as RatingBar).rating = nativeAd.starRating!!.toFloat()
            adView.starRatingView!!.visibility = View.VISIBLE
        }
        if (nativeAd.advertiser == null && adView.advertiserView != null) {
            adView.advertiserView!!.visibility = View.INVISIBLE
        } else if (adView.advertiserView != null) {
            (adView.advertiserView as TextView).text = nativeAd.advertiser
            adView.advertiserView!!.visibility = View.VISIBLE
        }
        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad. The SDK will populate the adView's MediaView
        // with the media content from this native ad.
        adView.setNativeAd(nativeAd)

    }


}