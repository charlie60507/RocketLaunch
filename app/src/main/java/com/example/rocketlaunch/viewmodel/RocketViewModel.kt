package com.example.rocketlaunch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketlaunch.data.FlightOrder
import com.example.rocketlaunch.data.RocketInfo
import com.example.rocketlaunch.persistence.DataRepository
import kotlinx.coroutines.launch

class RocketViewModel(private val repository: DataRepository) : ViewModel() {
    private var _info = MutableLiveData<RocketInfo>()
    val into: LiveData<RocketInfo>
        get() = _info

    fun getRocketInfo() {
        viewModelScope.launch {
            _info.value = repository.getRocketInfo()
        }
    }

    fun reorder(order: FlightOrder) {
        viewModelScope.launch {
            _info.value = repository.reorder(_info.value, order)
        }
    }
}
