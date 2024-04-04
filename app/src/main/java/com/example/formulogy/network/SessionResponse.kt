package com.example.formulogy.network

import kotlinx.serialization.Serializable

@Serializable
data class SessionResponse(
    val circuit_key: Int,
    val circuit_short_name: String,
    val country_code: String,
    val country_key: Int,
    val country_name: String,
    val date_end: String,
    val date_start: String,
    val gmt_offset: String,
    val location: String,
    val meeting_key: Int,
    val session_key: Int,
    val session_name: String,
    val session_type: String,
    val year: Int
)