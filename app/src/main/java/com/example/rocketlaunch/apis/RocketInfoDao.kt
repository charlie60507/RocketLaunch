package com.example.rocketlaunch.apis

import com.example.rocketlaunch.data.RocketInfo
import retrofit2.http.GET

interface RocketInfoDao {
    @GET(ApiConstant.ROCKET_URL_PATH)
    suspend fun getRocketLaunch(): RocketInfo
}
