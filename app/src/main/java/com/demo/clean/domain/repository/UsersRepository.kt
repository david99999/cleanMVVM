package com.demo.clean.domain.repository

import com.demo.clean.domain.models.UserProfile
import com.demo.clean.domain.models.UserShortInfo

interface UsersRepository {
    suspend fun getUserProfile(userId: Int): UserProfile
    suspend fun getUsersList(): List<UserShortInfo>
}
