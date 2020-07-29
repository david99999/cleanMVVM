package com.demo.clean.data.datasources

import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.domain.models.UserProfile
import io.reactivex.Observable

interface RemoteUsersDataSource {
    fun getUsersList(): Observable<List<UserShortInfo>>
    fun getUserProfile(userId: Int): Observable<UserProfile>
}