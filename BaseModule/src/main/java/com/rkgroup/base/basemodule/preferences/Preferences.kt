package com.rkgroup.base.basemodule.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


internal class Preferences(mContext: Context) : IPreferences {
    private val preferences: SharedPreferences =
        mContext.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    override suspend fun <T> saveValue(key: String, value: T) = withContext(Dispatchers.IO) {
        val convertedValue = Gson().toJson(value)
        preferences.edit {
            putString(key, convertedValue)
        }
    }

    override suspend fun <T> getValue(key: String, default: T, objectClass: Class<T>): T =
        withContext(Dispatchers.IO) {
            val savedValue = preferences.getString(key, "")
            if (savedValue!!.isEmpty()) {
                return@withContext default
            }
            val result = Gson().fromJson(savedValue, objectClass)
            result
        }


}

