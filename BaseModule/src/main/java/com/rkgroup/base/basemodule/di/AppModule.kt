package com.rkgroup.base.basemodule.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.rkgroup.base.basemodule.preferences.IPreferences
import com.rkgroup.base.basemodule.preferences.Preferences
import com.rkgroup.base.basemodule.resources.IResources
import com.rkgroup.base.basemodule.resources.ResourceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provides(application: Application) = AndroidViewModel(application)

    @Provides
    @Singleton
    fun providesIPrefs(@ApplicationContext mContext: Context): IPreferences = Preferences(mContext)

    @Provides
    @Singleton
    fun providesIRes(@ApplicationContext mContext: Context): IResources = ResourceManager(mContext)

}