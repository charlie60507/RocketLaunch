package com.example.rocketlaunch.apis

import com.example.rocketlaunch.OkHttpSingleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitSingleton {
    companion object {
        @Volatile
        private var instance: Retrofit? = null

        fun getInstance(): Retrofit {
            return instance ?: synchronized(this) {
                instance ?: Retrofit.Builder()
                    .baseUrl(ApiConstant.ROCKET_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpSingleton.getInstance())
                    .build()
            }
        }
    }
}