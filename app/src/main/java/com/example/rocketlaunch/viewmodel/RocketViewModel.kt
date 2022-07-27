package com.example.rocketlaunch.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rocketlaunch.data.RocketInfo
import com.example.rocketlaunch.datamodel.RocketDataModel

class RocketViewModel : ViewModel() {
    companion object {
        private const val TAG = "RocketViewModel"
    }

    private val rocketDataModel = RocketDataModel()
    private var info = MutableLiveData<RocketInfo>()

    fun getRocketInfo():MutableLiveData<RocketInfo> {
        Log.d(TAG, "getRocketInfo")
        rocketDataModel.callRocketInfoApi { result ->
            Log.d(TAG, "getRocketInfo, post result=${result}")
            info.postValue(result)
        }
        return info
    }
}
