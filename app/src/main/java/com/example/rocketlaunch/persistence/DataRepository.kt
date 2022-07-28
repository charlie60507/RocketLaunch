package com.example.rocketlaunch.persistence

import com.example.rocketlaunch.apis.RemoteDatabase

object DataRepository {
    private val api = RemoteDatabase.provideRocketInfoDao
    suspend fun getRocketInfo() = api.getRocketLaunch()
}