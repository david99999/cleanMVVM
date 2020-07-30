package com.demo.clean.data.repositoryImpl

import android.util.Log
import com.demo.clean.data.datasources.RemoteUsersDataSource
import com.demo.clean.domain.models.UserProfile
import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.domain.repository.UsersRepository
import io.reactivex.Observable

class UsersRepositoryImpl(private val dataSource: RemoteUsersDataSource) : UsersRepository {

    var profiles = listOf<UserShortInfo>()

    override fun getUserProfile(userId: Int): Observable<UserProfile> {
        return dataSource.getUserProfile(userId)
    }

    override fun getUsersList(): Observable<List<UserShortInfo>> {
        if (profiles.isNotEmpty()) {
            return Observable.just(profiles)
                .doOnNext { Log.i("UsersRepositoryImpl", "returning cached items") }
                .mergeWith(dataSource.getUsersList())
                .doOnNext {
                    Log.i("UsersRepositoryImpl", "saving and returning items from api")
                    profiles = it
                }
        }
        return dataSource.getUsersList()
            .doOnNext {
                Log.i("UsersRepositoryImpl", "returning items straight from api")
                profiles = it
            }
    }
}