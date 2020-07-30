package com.demo.clean.data.datasources.impl

import com.demo.clean.data.datasources.RemoteUsersDataSource
import com.demo.clean.data.datasources.origins.remote.RemoteUsersApi
import com.demo.clean.data.models.network.UserProfile
import io.reactivex.Observable

class RemoteUsersDataSourceImpl(
    private val remoteApi: RemoteUsersApi
) : RemoteUsersDataSource {

    override fun getUsersList(): Observable<List<UserProfile>> {
        return remoteApi.getUsersList()
    }

    override fun getUserProfile(userId: Int): Observable<UserProfile> {
        return remoteApi.getUserProfile(userId)
    }
}