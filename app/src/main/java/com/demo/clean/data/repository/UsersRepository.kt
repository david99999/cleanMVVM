package com.demo.clean.data.repository

import com.demo.clean.domain.models.UserDetailedInfo
import com.demo.clean.domain.models.UserShortInfo
import io.reactivex.Observable

interface UsersRepository {
    fun getUserProfile(userId: Int): Observable<UserDetailedInfo>
    fun getUsersList(): Observable<List<UserShortInfo>>
}
