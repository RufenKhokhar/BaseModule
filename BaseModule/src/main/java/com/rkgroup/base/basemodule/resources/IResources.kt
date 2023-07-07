package com.rkgroup.base.basemodule.resources

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.annotation.AnyRes
import androidx.annotation.BoolRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import java.io.InputStream

interface IResources {
    fun getString(@StringRes res: Int, vararg args: Any): String
    fun getDrawable(@DrawableRes res: Int): Drawable?
    fun getColor(@ColorRes res: Int): Int
    fun openRaw(@RawRes res: Int): InputStream
    fun openAssets(name: String): InputStream
    fun getBoolean(@BoolRes res: Int):Boolean
    fun getFont(@FontRes res: Int):Typeface?
    fun getResourceName(@AnyRes res: Int):String
    fun getResourceIdentifier(resName:String):Int
}