package com.example.rocketlaunch.apis

import com.example.rocketlaunch.OkHttpSingleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitManager {
    companion object {

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ApiConstant.ROCKET_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpSingleton.getInstance())
            .build()
        val apiService: RocketApiService = retrofit.create(RocketApiService::class.java)
    }
}