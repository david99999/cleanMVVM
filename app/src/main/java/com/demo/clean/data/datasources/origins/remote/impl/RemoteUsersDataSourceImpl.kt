package com.demo.clean.data.datasources.origins.remote.impl

import com.demo.clean.data.datasources.origins.remote.RemoteUsersDataSource
import com.demo.clean.data.datasources.origins.remote.network.RemoteUsersApi
import com.demo.clean.data.models.network.UserProfile
import io.reactivex.Observable
import javax.inject.Inject

class RemoteUsersDataSourceImpl @Inject constructor(
    private val remoteApi: RemoteUsersApi
) : RemoteUsersDataSource {

    override fun getUsersList(): Observable<List<UserProfile>> {
        return remoteApi.getUsersList()
    }

    override fun getUserProfile(userId: Int): Observable<UserProfile> {
        return remoteApi.getUserProfile(userId)
    }
}