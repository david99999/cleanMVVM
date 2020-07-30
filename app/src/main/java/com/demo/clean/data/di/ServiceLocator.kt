package com.demo.clean.data.di

import com.demo.clean.data.datasources.RemoteUsersDataSource
import com.demo.clean.data.datasources.impl.RemoteUsersDataSourceImpl
import com.demo.clean.data.datasources.origins.local.UsersLocalDataSource
import com.demo.clean.data.datasources.origins.remote.RemoteUsersApi
import com.demo.clean.data.datasources.origins.remote.impl.ApiModule
import com.demo.clean.data.mappers.Mapper
import com.demo.clean.data.mappers.UserDetailedInfoMapper
import com.demo.clean.data.models.network.UserProfile
import com.demo.clean.data.repositoryImpl.UsersRepositoryImpl
import com.demo.clean.domain.models.UserDetailedInfo
import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.domain.repository.UsersRepository

object ServiceLocator {
    fun getRepository(
        localDataSource: UsersLocalDataSource,
        userMapper: Mapper<UserProfile, UserDetailedInfo>,
        usersListMapper: Mapper<UserProfile, UserShortInfo>
    ): UsersRepository =
        UsersRepositoryImpl(userMapper, usersListMapper, getDataSourceImpl(), localDataSource)

    private fun getDataSourceImpl(): RemoteUsersDataSource {
        return RemoteUsersDataSourceImpl(getRemoteApi())
    }

    private fun getRemoteApi(): RemoteUsersApi {
        return ApiModule.getApi()
    }

}