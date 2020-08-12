package com.demo.clean.data.di

import com.demo.clean.data.datasources.origins.remote.RemoteUsersDataSource
import com.demo.clean.data.datasources.origins.remote.impl.RemoteUsersDataSourceImpl
import com.demo.clean.data.datasources.origins.local.LocalUsersDataSource
import com.demo.clean.data.datasources.origins.remote.network.RemoteUsersApi
import com.demo.clean.data.datasources.origins.remote.network.ApiModule
import com.demo.clean.data.repository.impl.UsersRepositoryImpl
import com.demo.clean.data.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun getRepository(
        usersLocalDataSource: LocalUsersDataSource,
        remoteUsersDataSource: RemoteUsersDataSource
    ): UsersRepository =
        UsersRepositoryImpl(remoteUsersDataSource, usersLocalDataSource)

    @Singleton
    @Provides
    fun getRemoteDataSourceImpl(): RemoteUsersDataSource {
        return RemoteUsersDataSourceImpl(
            getRemoteApi()
        )
    }

    private fun getRemoteApi(): RemoteUsersApi {
        return ApiModule.getApi()
    }
}