package com.demo.clean.usecases.di

import android.app.Application
import com.demo.clean.data.di.ServiceLocator
import com.demo.clean.framework.di.FrameworkProvider
import com.demo.clean.usecases.GetUserInfoUseCase
import com.demo.clean.usecases.GetUsersUseCase

object UseCasesProvider {
    fun getUserInfoUseCase(application: Application): GetUserInfoUseCase {
        return GetUserInfoUseCase(
            ServiceLocator.getRepository(
                FrameworkProvider.getLocalDataSource(application),
                ServiceLocator.getRemoteDataSourceImpl()
            )
        )
    }

    fun getUsersUseCase(application: Application): GetUsersUseCase {
        return GetUsersUseCase(
            ServiceLocator.getRepository(
                FrameworkProvider.getLocalDataSource(application),
                ServiceLocator.getRemoteDataSourceImpl()
            )
        )
    }
}