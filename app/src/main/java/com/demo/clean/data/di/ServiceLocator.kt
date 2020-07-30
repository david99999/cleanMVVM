package com.demo.clean.data.di

import com.demo.clean.data.datasources.RemoteUsersDataSource
import com.demo.clean.data.datasources.impl.RemoteUsersDataSourceImpl
import com.demo.clean.data.datasources.origins.local.UsersLocalDataSource
import com.demo.clean.data.datasources.origins.remote.RemoteUsersApi
import com.demo.clean.data.datasources.origins.remote.impl.ApiModule
import com.demo.clean.data.repositoryImpl.UsersRepositoryImpl
import com.demo.clean.domain.repository.UsersRepository

object ServiceLocator {
    fun getRepository(
        usersLocalDataSource: UsersLocalDataSource,
        remoteUsersDataSource: RemoteUsersDataSource
    ): UsersRepository =
        UsersRepositoryImpl(remoteUsersDataSource, usersLocalDataSource)

     fun getRemoteDataSourceImpl(): RemoteUsersDataSource {
        return RemoteUsersDataSourceImpl(getRemoteApi())
    }

    private fun getRemoteApi(): RemoteUsersApi {
        return ApiModule.getApi()
    }
}