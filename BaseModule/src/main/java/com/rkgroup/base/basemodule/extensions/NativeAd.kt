package com.rkgroup.base.basemodule.extensions

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import com.rkgroup.base.basemodule.R

fun NativeAd.populateNativeAdView(adView: NativeAdView) {
    // Set the media view. Media content will be automatically populated in the media view once
    // Set other ad assets
    adView.headlineView = adView.findViewById(R.id.ad_headline)
    val mediaView: MediaView? = adView.findViewById(R.id.ad_media)
    adView.mediaView = mediaView
    adView.bodyView = adView.findViewById(R.id.ad_body)
    adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
    adView.iconView = adView.findViewById(R.id.ad_app_icon)
    adView.priceView = adView.findViewById(R.id.ad_price)
    adView.starRatingView = adView.findViewById(R.id.ad_stars)
    adView.storeView = adView.findViewById(R.id.ad_store)
    adView.advertiserView = adView.findViewById(R.id.ad_advertiser)

    // The headline is guaranteed to be in every NativeAd.
    if (headline == null && adView.headlineView != null) {
        adView.headlineView!!.visibility = View.INVISIBLE
    } else if (adView.headlineView != null) {
        adView.headlineView!!.visibility = View.VISIBLE
        (adView.headlineView as TextView).text = headline
        adView.headlineView!!.isSelected = true
    }

    // These assets aren't guaranteed to be in every NativeAd, so it's important to
    // check before trying to display them.
    if (body == null && adView.bodyView != null) {
        adView.bodyView!!.visibility = View.INVISIBLE
    } else if (adView.bodyView != null) {
        adView.bodyView!!.visibility = View.VISIBLE
        (adView.bodyView as TextView).text = body
        adView.bodyView!!.isSelected = true
    }
    if (callToAction == null && adView.callToActionView != null) {
        adView.callToActionView!!.visibility = View.INVISIBLE
    } else if (adView.callToActionView != null) {
        adView.callToActionView!!.visibility = View.VISIBLE
        (adView.callToActionView as Button).text = callToAction
    }
    if (icon == null && adView.iconView != null) {
        if (images.isNotEmpty()) {
            adView.iconView!!.visibility = View.VISIBLE
            (adView.iconView as ImageView).setImageDrawable(images[0]!!.drawable)
        } else {
            adView.iconView!!.visibility = View.GONE
        }
    } else if (adView.iconView != null) {
        adView.iconView!!.visibility = View.VISIBLE
        (adView.iconView as ImageView).setImageDrawable(icon!!.drawable)
    }
    if (price == null && adView.priceView != null) {
        adView.priceView!!.visibility = View.INVISIBLE
    } else if (adView.priceView != null) {
        adView.priceView!!.visibility = View.VISIBLE
        (adView.priceView as TextView).text = price
    }
    if (store == null && adView.storeView != null) {
        adView.storeView!!.visibility = View.INVISIBLE
    } else if (adView.storeView != null) {
        adView.storeView!!.visibility = View.VISIBLE
        (adView.storeView as TextView).text = store
    }
    if (starRating == null && adView.starRatingView != null) {
        adView.starRatingView!!.visibility = View.INVISIBLE
    } else if (adView.starRatingView != null) {
        (adView.starRatingView as RatingBar).rating = starRating!!.toFloat()
        adView.starRatingView!!.visibility = View.VISIBLE
    }
    if (advertiser == null && adView.advertiserView != null) {
        adView.advertiserView!!.visibility = View.INVISIBLE
    } else if (adView.advertiserView != null) {
        (adView.advertiserView as TextView).text = advertiser
        adView.advertiserView!!.visibility = View.VISIBLE
    }
    // This method tells the Google Mobile Ads SDK that you have finished populating your
    // native ad view with this native ad. The SDK will populate the adView's MediaView
    // with the media content from this native ad.
    adView.setNativeAd(this)
}
