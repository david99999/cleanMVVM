package com.demo.clean.data.datasources.remote

import com.demo.clean.data.datasources.remote.Endpoints.GET_USERS
import com.demo.clean.data.datasources.remote.Endpoints.GET_USER_PROFILE
import com.demo.clean.domain.models.UserProfile
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteUsersApi {
    @GET(GET_USERS)
    suspend fun getUsersList(): List<UserProfile>

    @GET(GET_USER_PROFILE)
    suspend fun getUserProfile(
        @Path("userId") userId: Int
    ): UserProfile
}