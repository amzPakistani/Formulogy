package com.example.formulogy.network

import kotlinx.serialization.Serializable

@Serializable
data class DriverResponse(
    val broadcast_name: String,
    val country_code: String?,
    val driver_number: Int,
    val first_name: String?,
    val full_name: String,
    val headshot_url: String?,
    val last_name: String?,
    val meeting_key: Int,
    val name_acronym: String,
    val session_key: Int,
    var team_colour: String?,
    val team_name: String?
)