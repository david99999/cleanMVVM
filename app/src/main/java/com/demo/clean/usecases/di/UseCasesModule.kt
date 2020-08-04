package com.demo.clean.usecases.di

import com.demo.clean.domain.repository.UsersRepository
import com.demo.clean.usecases.GetUserInfoUseCase
import com.demo.clean.usecases.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class UseCasesModule {
    @Provides
    fun getUserInfoUseCase(repo: UsersRepository): GetUserInfoUseCase {
        return GetUserInfoUseCase(repo)
    }

    @Provides
    fun getUsersUseCase(repo: UsersRepository): GetUsersUseCase {
        return GetUsersUseCase(repo)
    }
}