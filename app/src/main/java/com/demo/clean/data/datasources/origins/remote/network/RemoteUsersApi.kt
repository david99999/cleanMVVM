package com.demo.clean.data.datasources.origins.remote.network

import com.demo.clean.data.datasources.origins.remote.network.Endpoints.GET_USERS
import com.demo.clean.data.datasources.origins.remote.network.Endpoints.GET_USER_PROFILE
import com.demo.clean.data.models.network.UserProfile
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteUsersApi {
    @GET(GET_USERS)
     fun getUsersList(): Observable<List<UserProfile>>

    @GET(GET_USER_PROFILE)
     fun getUserProfile(
        @Path("userId") userId: Int
    ): Observable<UserProfile>
}