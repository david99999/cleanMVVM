package com.demo.clean.data.repositoryImpl

import com.demo.clean.data.datasources.RemoteUsersDataSource
import com.demo.clean.domain.models.UserProfile
import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.domain.repository.UsersRepository
import io.reactivex.Observable

class UsersRepositoryImpl(private val dataSource: RemoteUsersDataSource) : UsersRepository {
    override fun getUserProfile(userId: Int): Observable<UserProfile> {
        return dataSource.getUserProfile(userId)
    }

    override fun getUsersList(): Observable<List<UserShortInfo>> {
        return dataSource.getUsersList()
    }
}