package com.rkgroup.base.basemodule

import android.app.Application
import android.content.Context
import com.rkgroup.base.basemodule.preferences.IPreferences
import com.rkgroup.base.basemodule.preferences.Preferences
import com.rkgroup.base.basemodule.resources.IResources
import com.rkgroup.base.basemodule.resources.ResourceManager
import com.rkgroup.base.basemodule.utils.AppLanguageChanger

abstract class BaseApplication : Application() {


    private lateinit var _preferences: IPreferences
    val preferences: IPreferences get() = _preferences

    private lateinit var _resourcesManager: IResources
    val resourcesManager: IResources get() = _resourcesManager


    override fun onCreate() {
        super.onCreate()
        _preferences = Preferences(this)
        _resourcesManager = ResourceManager(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(AppLanguageChanger.wrapContext(base))
    }


}