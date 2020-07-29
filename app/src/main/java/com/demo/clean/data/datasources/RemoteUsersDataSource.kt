package com.demo.clean.data.datasources

import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.domain.models.UserProfile

interface RemoteUsersDataSource {
    suspend fun getUsersList(): List<UserShortInfo>
    suspend fun getUserProfile(userId: Int): UserProfile
}