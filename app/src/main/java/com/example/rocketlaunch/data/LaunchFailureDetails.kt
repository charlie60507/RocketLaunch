package com.example.rocketlaunch.data

data class LaunchFailureDetails(
    val altitude: Int,
    val reason: String,
    val time: Int
)