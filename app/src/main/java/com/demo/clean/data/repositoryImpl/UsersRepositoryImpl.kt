package com.demo.clean.data.repositoryImpl

import android.util.Log
import com.demo.clean.data.datasources.RemoteUsersDataSource
import com.demo.clean.data.datasources.origins.local.UsersLocalDataSource
import com.demo.clean.data.mappers.Mapper
import com.demo.clean.data.models.network.UserProfile
import com.demo.clean.domain.models.UserDetailedInfo
import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.domain.repository.UsersRepository
import io.reactivex.Observable

class UsersRepositoryImpl(
    private val userDetailedInfoMapper: Mapper<UserProfile, UserDetailedInfo>,
    private val usersInfoMapper: Mapper<UserProfile, UserShortInfo>,
    private val remoteDataSource: RemoteUsersDataSource,
    private val localDataSource: UsersLocalDataSource
) : UsersRepository {

    override fun getUserProfile(userId: Int): Observable<UserDetailedInfo> {
        return remoteDataSource.getUserProfile(userId)
            .map { userDetailedInfoMapper.map(it) }
    }

    override fun getUsersList(): Observable<List<UserShortInfo>> {
        return localDataSource.getUsersList()
            .flatMap { Observable.fromIterable(it) }
            .map { usersInfoMapper.map(it) }
            .toList()
            .toObservable()
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
                .map { usersInfoMapper.map(it) }
                .toList()
                .toObservable())
    }
}