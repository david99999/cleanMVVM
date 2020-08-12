package com.demo.clean.data.repository.impl

import android.util.Log
import com.demo.clean.data.datasources.origins.remote.RemoteUsersDataSource
import com.demo.clean.data.datasources.origins.local.LocalUsersDataSource
import com.demo.clean.data.models.network.toUserDetailedInfo
import com.demo.clean.data.models.network.toUserShortInfo
import com.demo.clean.domain.models.UserDetailedInfo
import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.data.repository.UsersRepository
import io.reactivex.Observable
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteUsersDataSource,
    private val localDataSource: LocalUsersDataSource
) : UsersRepository {

    override fun getUserProfile(userId: Int): Observable<UserDetailedInfo> {
        return remoteDataSource.getUserProfile(userId)
            .map { it.toUserDetailedInfo() }
    }

    override fun getUsersList(): Observable<List<UserShortInfo>> {
        return localDataSource.getUsersList()
            .flatMap {
                Observable.fromIterable(it)
                    .map { item -> item.toUserShortInfo() }
                    .toList()
                    .toObservable()
            }
            .doOnNext { Log.i("UsersRepositoryImpl", "returning ${it.size} local items") }
            .mergeWith(remoteDataSource.getUsersList()
                .doOnNext {
                    Log.i(
                        "UsersRepositoryImpl",
                        "saving and returning ${it.size} items from api"
                    )
                    localDataSource.saveUsers(it)
                }
                .flatMap {
                    Log.i("RemoteDataSourceImpl", "waiting 5 seconds for returning the api")
                    Thread.sleep(5000)
                    Log.i("RemoteDataSourceImpl", "returning the api")
                    Observable.fromIterable(it)
                }
                .map { it.toUserShortInfo() }
                .toList()
                .toObservable()
            )
    }
}
