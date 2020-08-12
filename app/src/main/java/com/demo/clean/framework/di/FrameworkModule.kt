package com.demo.clean.framework.di

import android.app.Application
import android.content.Context
import androidx.hilt.Assisted
import com.demo.clean.data.datasources.origins.local.LocalUsersDataSource
import com.demo.clean.framework.room.UsersDatabase
import com.demo.clean.framework.sharedpreferences.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class FrameworkModule {

    @Provides
    fun getLocalDataSource(@Assisted application: Application): LocalUsersDataSource {
        return UsersDatabase.getUsersDao(application)
    }

    @Singleton
    @Provides
    fun getPreferencesManager(@Assisted application: Application): PreferencesManager {
        return PreferencesManager(
            application.getSharedPreferences(
                PreferencesManager.PREFERENCES_NAME,
                Context.MODE_PRIVATE
            )
        )
    }
}