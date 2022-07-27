package com.example.rocketlaunch.datamodel

import android.R.attr
import android.util.Log
import com.example.rocketlaunch.apis.RetrofitManager
import com.example.rocketlaunch.data.RocketInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.graphics.BitmapFactory

import android.graphics.Bitmap

import android.R.attr.src
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors


class RocketDataModel {
    companion object {
        private const val TAG = "RocketDataModel"
    }

    fun callRocketInfoApi(callback: (RocketInfo?) -> Unit) {
        Log.d(TAG, "callRocketInfoApi")
        val call = RetrofitManager.apiService.getRocketLaunch()
        call.enqueue(object : Callback<RocketInfo?> {
            override fun onResponse(call: Call<RocketInfo?>?, response: Response<RocketInfo?>?) {
                response?.body()?.let {
                    callback(it)
                }
            }

            override fun onFailure(call: Call<RocketInfo?>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }
}
