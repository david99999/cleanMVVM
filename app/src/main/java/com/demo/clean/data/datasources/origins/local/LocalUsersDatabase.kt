package com.demo.clean.data.datasources.origins.local

import androidx.room.*
import com.demo.clean.domain.models.UserShortInfo
import io.reactivex.Observable


@Database(entities = [UserShortInfo::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersLocalDataSource
}

@Dao
interface UsersLocalDataSource {
    @Query("SELECT * FROM UserShortInfo")
    fun getUsersList(): Observable<List<UserShortInfo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveUsers(users: List<UserShortInfo>)
}
