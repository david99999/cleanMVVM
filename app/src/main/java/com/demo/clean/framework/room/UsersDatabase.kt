package com.demo.clean.framework.room

import android.app.Application
import androidx.room.Room
import com.demo.clean.data.datasources.origins.local.AppDatabase

object UsersDatabase {
    fun getUsersDao(application: Application) =
        Room.databaseBuilder(application, AppDatabase::class.java, "users.db").build().usersDao()
}