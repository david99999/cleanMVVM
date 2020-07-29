package com.demo.clean.usecases

import com.demo.clean.domain.models.UserProfile
import com.demo.clean.domain.repository.UsersRepository
import io.reactivex.Observable

class GetUserInfoUseCase(private val repository: UsersRepository) {
    fun GetUserProfile(userId: Int): Observable<UserProfile> {
        return repository.getUserProfile(userId)
    }
}