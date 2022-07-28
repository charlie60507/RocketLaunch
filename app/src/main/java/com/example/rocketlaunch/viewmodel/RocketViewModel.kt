package com.example.rocketlaunch.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.rocketlaunch.persistence.DataRepository

class RocketViewModel(private val repository: DataRepository) : ViewModel() {
    companion object {
        private const val TAG = "RocketViewModel"
    }

    fun getRocketInfo() = liveData {
        Log.d(TAG, "getRocketInfo")
        emit(repository.getRocketInfo())
    }
}
