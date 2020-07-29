package com.demo.clean.usecases

import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.domain.repository.UsersRepository

class GetUsersUseCase(private val repository: UsersRepository) {
    suspend fun getUsersList(): List<UserShortInfo> {
        return repository.getUsersList()
    }
}