package com.demo.clean.data.datasources.origins.local

import androidx.room.*
import com.demo.clean.data.models.network.UserProfile
import com.demo.clean.domain.models.UserShortInfo
import io.reactivex.Observable


@Database(entities = [UserProfile::class], version = 1, exportSchema = false)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersLocalDataSource
}

@Dao
interface UsersLocalDataSource {
    @Query("SELECT * FROM UserProfile")
    fun getUsersList(): Observable<List<UserProfile>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveUsers(users: List<UserProfile>)
}
