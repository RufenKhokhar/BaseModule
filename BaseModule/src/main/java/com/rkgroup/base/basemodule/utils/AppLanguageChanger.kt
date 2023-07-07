package com.rkgroup.base.basemodule.utils

import android.content.Context
import android.content.res.Configuration
import androidx.core.content.edit
import java.util.Locale

const val KEY_LOCAL = "local"

object AppLanguageChanger {
    @JvmStatic
    fun wrapContext(context: Context): Context =
        setAppLanguage(context, getSavedLanguage(context))

    @JvmStatic
    fun changeLanguage(mContext: Context, langCode: String) =
        mContext.getSharedPreferences("app_local", Context.MODE_PRIVATE).edit {
            putString(KEY_LOCAL, langCode)
        }

    fun getSavedLanguage(mContext: Context): String {
        return mContext.getSharedPreferences("app_local", Context.MODE_PRIVATE)
            .getString(KEY_LOCAL, "en")!!
    }

    // application languages update
    private fun setAppLanguage(mContext: Context, languageCode: String): Context {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val configuration: Configuration = mContext.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return mContext.createConfigurationContext(configuration)
    }
}