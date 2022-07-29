package com.example.rocketlaunch.persistence

import android.util.Log
import com.example.rocketlaunch.apis.RemoteDatabase
import com.example.rocketlaunch.data.FlightOrder
import com.example.rocketlaunch.data.RocketInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DataRepository {
    private const val TAG = "DataRepository"
    private val api = RemoteDatabase.provideRocketInfoDao
    suspend fun getRocketInfo() = api.getRocketLaunch()
    suspend fun reorder(info: RocketInfo?, order: FlightOrder) = withContext(Dispatchers.IO) {
        Log.d(TAG, "reorder")
        when (order) {
            FlightOrder.OLDEST -> {
                info?.sortBy { it.flightNumber }
                info
            }
            FlightOrder.NEWEST -> {
                info?.sortByDescending { it.flightNumber }
                info
            }
        }
    }
}