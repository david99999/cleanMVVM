package com.demo.clean.data.datasources.origins.remote

import com.demo.clean.data.models.network.UserProfile
import io.reactivex.Observable

interface RemoteUsersDataSource {
    fun getUsersList(): Observable<List<UserProfile>>
    fun getUserProfile(userId: Int): Observable<UserProfile>
}