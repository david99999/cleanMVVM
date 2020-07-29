package com.demo.clean.data.datasources.remote.impl

import com.demo.clean.data.datasources.remote.Endpoints
import com.demo.clean.data.datasources.remote.RemoteUsersApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiModule {
    fun getApi(): RemoteUsersApi {
        return Retrofit.Builder()
            .baseUrl(Endpoints.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RemoteUsersApi::class.java)
    }
}