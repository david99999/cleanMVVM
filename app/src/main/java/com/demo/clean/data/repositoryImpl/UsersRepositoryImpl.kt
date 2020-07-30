package com.demo.clean.data.repositoryImpl

import android.util.Log
import com.demo.clean.data.datasources.RemoteUsersDataSource
import com.demo.clean.data.datasources.origins.local.UsersLocalDataSource
import com.demo.clean.domain.models.UserProfile
import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.domain.repository.UsersRepository
import io.reactivex.Observable

class UsersRepositoryImpl(
    private val remoteDataSource: RemoteUsersDataSource,
    private val localDataSource: UsersLocalDataSource
) : UsersRepository {

    override fun getUserProfile(userId: Int): Observable<UserProfile> {
        return remoteDataSource.getUserProfile(userId)
    }

    override fun getUsersList(): Observable<List<UserShortInfo>> {
        return localDataSource.getUsersList()
            .doOnNext { Log.i("UsersRepositoryImpl", "returning ${it.size} local items") }
            .mergeWith(remoteDataSource.getUsersList())
            .doOnNext {
                Log.i("UsersRepositoryImpl", "saving and returning ${it.size} items from api")
                localDataSource.saveUsers(it)
            }
    }
}