package com.example.formulogy.network

import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val air_temperature: Double,
    val date: String,
    val humidity: Int,
    val meeting_key: Int,
    val pressure: Double,
    val rainfall: Int,
    val session_key: Int,
    val track_temperature: Double,
    val wind_direction: Int,
    val wind_speed: Double
)