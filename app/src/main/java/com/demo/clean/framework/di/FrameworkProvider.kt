package com.demo.clean.framework.di

import android.app.Application
import com.demo.clean.data.datasources.origins.local.UsersLocalDataSource
import com.demo.clean.framework.room.UsersDatabase

object FrameworkProvider {

    fun getLocalDataSource(application: Application): UsersLocalDataSource {
        return UsersDatabase.getUsersDao(application)
    }
}