package com.demo.clean.framework.room

import androidx.room.*
import com.demo.clean.data.datasources.origins.local.LocalUsersDataSource
import com.demo.clean.data.models.network.UserProfile


@Database(entities = [UserProfile::class], version = 1, exportSchema = false)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): LocalUsersDataSource
}
