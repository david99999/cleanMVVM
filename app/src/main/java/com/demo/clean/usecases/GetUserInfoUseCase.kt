package com.demo.clean.usecases

import com.demo.clean.domain.models.UserProfile
import com.demo.clean.domain.repository.UsersRepository

class GetUserInfoUseCase(private val repository: UsersRepository) {
    suspend fun GetUserProfile(userId: Int): UserProfile {
        return repository.getUserProfile(userId)
    }
}