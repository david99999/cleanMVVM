package com.demo.clean.data.di

import com.demo.clean.data.datasources.RemoteUsersDataSource
import com.demo.clean.data.datasources.impl.RemoteUsersDataSourceImpl
import com.demo.clean.data.datasources.origins.local.UsersLocalDataSource
import com.demo.clean.data.datasources.origins.remote.RemoteUsersApi
import com.demo.clean.data.datasources.origins.remote.impl.ApiModule
import com.demo.clean.data.repositoryImpl.UsersRepositoryImpl
import com.demo.clean.domain.repository.UsersRepository
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
        usersLocalDataSource: UsersLocalDataSource,
        remoteUsersDataSource: RemoteUsersDataSource
    ): UsersRepository =
        UsersRepositoryImpl(remoteUsersDataSource, usersLocalDataSource)

    @Singleton
    @Provides
    fun getRemoteDataSourceImpl(): RemoteUsersDataSource {
        return RemoteUsersDataSourceImpl(getRemoteApi())
    }

    private fun getRemoteApi(): RemoteUsersApi {
        return ApiModule.getApi()
    }
}