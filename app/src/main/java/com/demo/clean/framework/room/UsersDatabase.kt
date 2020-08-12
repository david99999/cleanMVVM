package com.demo.clean.framework.room

import android.app.Application
import androidx.room.Room

object UsersDatabase {
    fun getUsersDao(application: Application) =
        Room.databaseBuilder(application, AppDatabase::class.java, "users.db").build().usersDao()
}