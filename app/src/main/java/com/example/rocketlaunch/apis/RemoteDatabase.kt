package com.example.rocketlaunch.apis

object RemoteDatabase {
    val provideRocketInfoDao: RocketInfoDao =
        RetrofitSingleton.getInstance().create(RocketInfoDao::class.java)
}