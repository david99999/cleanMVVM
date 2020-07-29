package com.demo.clean.data.repositoryImpl

import com.demo.clean.data.datasources.RemoteUsersDataSource
import com.demo.clean.domain.models.UserProfile
import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.domain.repository.UsersRepository

class UsersRepositoryImpl(private val dataSource: RemoteUsersDataSource) : UsersRepository {
    override suspend fun getUserProfile(userId: Int): UserProfile {
        return dataSource.getUserProfile(userId)
    }

    override suspend fun getUsersList(): List<UserShortInfo> {
        return dataSource.getUsersList()
    }
}