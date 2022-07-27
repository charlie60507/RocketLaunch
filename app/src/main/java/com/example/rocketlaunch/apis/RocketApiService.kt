package com.example.rocketlaunch.apis

import com.example.rocketlaunch.data.RocketInfo
import retrofit2.Call
import retrofit2.http.GET

interface RocketApiService {
    @GET(ApiConstant.ROCKET_URL_PATH)
    fun getRocketLaunch(): Call<RocketInfo?>
}
