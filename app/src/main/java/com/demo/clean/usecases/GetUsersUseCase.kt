package com.demo.clean.usecases

import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.domain.repository.UsersRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: UsersRepository) {

    fun getUsersList(): Observable<List<UserShortInfo>> {
        return repository.getUsersList()
    }
}