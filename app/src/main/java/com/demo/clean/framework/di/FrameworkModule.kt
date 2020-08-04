package com.demo.clean.framework.di

import android.app.Application
import androidx.hilt.Assisted
import com.demo.clean.data.datasources.origins.local.UsersLocalDataSource
import com.demo.clean.framework.room.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class FrameworkModule {

    @Provides
    fun getLocalDataSource(@Assisted application: Application): UsersLocalDataSource {
        return UsersDatabase.getUsersDao(application)
    }
}