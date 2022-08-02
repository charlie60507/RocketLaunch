package com.example.rocketlaunch.network

import com.example.rocketlaunch.data.RocketInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface RocketInfoService {
    @GET(ApiConstant.ROCKET_URL_PATH)
    suspend fun getRocketLaunch(): RocketInfo
}

object RocketInfoNetwork {
    val apiService: RocketInfoService =
        Retrofit.Builder()
            .baseUrl(ApiConstant.ROCKET_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpSingleton.instance)
            .build().create(RocketInfoService::class.java)
}
