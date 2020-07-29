package com.demo.clean.data.datasources.impl

import com.demo.clean.data.datasources.RemoteUsersDataSource
import com.demo.clean.data.datasources.remote.RemoteUsersApi
import com.demo.clean.data.mappers.UserProfileMapper
import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.domain.models.UserProfile

class RemoteUsersDataSourceImpl(
    private val shortUserInfoMapper: UserProfileMapper,
    private val remoteApi: RemoteUsersApi
) : RemoteUsersDataSource {

    override suspend fun getUsersList(): List<UserShortInfo> {
        return remoteApi.getUsersList().map { shortUserInfoMapper(it) }
    }

    override suspend fun getUserProfile(userId: Int): UserProfile {
        return remoteApi.getUserProfile(userId)
    }
}