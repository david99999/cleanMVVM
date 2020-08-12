package com.demo.clean.usecases

import com.demo.clean.domain.models.UserDetailedInfo
import com.demo.clean.data.repository.UsersRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(private val repository: UsersRepository) {
    fun getUserProfile(userId: Int): Observable<UserDetailedInfo> {
        return repository.getUserProfile(userId)
    }
}