package com.rkgroup.base.basemodule

import android.app.Application
import android.content.Context
import com.rkgroup.base.basemodule.preferences.IPreferences
import com.rkgroup.base.basemodule.resources.IResources
import com.rkgroup.base.basemodule.utils.AppLanguageChanger
import javax.inject.Inject

abstract class BaseApplication : Application() {

    @Inject
    lateinit var preferences: IPreferences

    @Inject
    lateinit var resourcesManager: IResources

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(AppLanguageChanger.wrapContext(base))
    }


}