package com.demo.clean.usecases

import com.demo.clean.data.models.network.UserProfile
import com.demo.clean.domain.models.UserDetailedInfo
import com.demo.clean.domain.repository.UsersRepository
import io.reactivex.Observable

class GetUserInfoUseCase(private val repository: UsersRepository) {
    fun GetUserProfile(userId: Int): Observable<UserDetailedInfo> {
        return repository.getUserProfile(userId)
    }
}