package com.rkgroup.base.basemodule.resources

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import java.io.InputStream

class ResourceManager(private val mContext: Context) : IResources {
    override fun getString(res: Int, vararg args: Any): String = mContext.getString(res, args)

    override fun getDrawable(res: Int): Drawable? = AppCompatResources.getDrawable(mContext, res)

    override fun getColor(res: Int): Int = ContextCompat.getColor(mContext, res)

    override fun openRaw(res: Int): InputStream = mContext.resources.openRawResource(res)

    override fun openAssets(name: String): InputStream = mContext.resources.assets.open(name)
    override fun getBoolean(res: Int): Boolean = mContext.resources.getBoolean(res)

    override fun getFont(res: Int): Typeface? = ResourcesCompat.getFont(mContext, res)
    override fun getResourceName(res: Int): String = mContext.resources.getResourceName(res)
    @SuppressLint("DiscouragedApi")
    override fun getResourceIdentifier(resName: String): Int = mContext.resources.getIdentifier(resName,null,null)


}