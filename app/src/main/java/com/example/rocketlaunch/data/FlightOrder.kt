package com.example.rocketlaunch.data

enum class FlightOrder(var value: Int) {
    OLDEST(0), NEWEST(1);

    companion object {
        fun fromInt(value: Int) = FlightOrder.values().first { it.value == value }
    }
}