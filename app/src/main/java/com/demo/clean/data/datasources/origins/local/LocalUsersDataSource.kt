package com.demo.clean.data.datasources.origins.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.clean.data.models.network.UserProfile
import io.reactivex.Observable

@Dao
interface LocalUsersDataSource {
    @Query("SELECT * FROM UserProfile")
    fun getUsersList(): Observable<List<UserProfile>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveUsers(users: List<UserProfile>)
}