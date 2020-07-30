package com.demo.clean.data.datasources.impl

import android.util.Log
import com.demo.clean.data.datasources.RemoteUsersDataSource
import com.demo.clean.data.datasources.remote.RemoteUsersApi
import com.demo.clean.data.mappers.UserProfileMapper
import com.demo.clean.domain.models.UserProfile
import com.demo.clean.domain.models.UserShortInfo
import io.reactivex.Observable

class RemoteUsersDataSourceImpl(
    private val shortUserInfoMapper: UserProfileMapper,
    private val remoteApi: RemoteUsersApi
) : RemoteUsersDataSource {

    override fun getUsersList(): Observable<List<UserShortInfo>> {
        return remoteApi.getUsersList()
            .flatMap {
                Log.i("RemoteDataSourceImpl", "waiting 5 seconds for returning the api")
                Thread.sleep(5000)
                Log.i("RemoteDataSourceImpl", "returning the api")
                Observable.fromIterable(it)
            }
            .map { shortUserInfoMapper(it) }
            .toList()
            .toObservable()
    }

    override fun getUserProfile(userId: Int): Observable<UserProfile> {
        return remoteApi.getUserProfile(userId)
    }
}