package com.rkgroup.base.basemodule.preferences

import android.os.Parcelable
import java.io.Serializable

interface IPreferences {
    suspend fun <T> saveValue(key: String, value: T)
    suspend fun < T> getValue(key: String, default: T,objectClass:Class<T> ): T


}

