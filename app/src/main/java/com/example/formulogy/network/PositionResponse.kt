package com.example.formulogy.network

import kotlinx.serialization.Serializable

@Serializable
data class PositionResponse(
    val date: String,
    val driver_number: Int,
    val meeting_key: Int,
    val position: Int,
    val session_key: Int
)