package com.demo.clean.domain.repository

import com.demo.clean.domain.models.UserProfile
import com.demo.clean.domain.models.UserShortInfo
import io.reactivex.Observable

interface UsersRepository {
    fun getUserProfile(userId: Int): Observable<UserProfile>
    fun getUsersList(): Observable<List<UserShortInfo>>
}
