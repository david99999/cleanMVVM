package com.demo.clean.usecases.di

import com.demo.clean.data.di.ServiceLocator
import com.demo.clean.usecases.GetUserInfoUseCase

object UseCasesProvider {
    fun getUserInfoUseCase():GetUserInfoUseCase{
        return GetUserInfoUseCase(ServiceLocator.getRepository())
    }
    fun getUsersUseCase()
}