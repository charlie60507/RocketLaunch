package com.example.rocketlaunch

import com.example.rocketlaunch.apis.ApiConstant
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class OkHttpSingleton {
    companion object {
        @Volatile
        private var instance: OkHttpClient? = null

        fun getInstance(): OkHttpClient {
            return instance ?: synchronized(this) {
                instance ?: OkHttpClient().newBuilder()
                    .connectTimeout(ApiConstant.OKHTTP_TIMEOUT, TimeUnit.SECONDS)
                    .build().also { instance = it }
            }
        }
    }
}