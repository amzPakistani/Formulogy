package com.example.formulogy.network

import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("sessions?year=2024")
    suspend fun getSessions(@Query("meeting_key") meetingkey: String?): List<SessionResponse>

    @GET("position?meeting_key=latest")
    suspend fun getPositions(): List<PositionResponse>

    @GET("meetings?year=2024")
    suspend fun getMeetings(@Query("meeting_key") meetingkey: String?): List<MeetingResponse>

    @GET("drivers?session_key=9158")
    suspend fun getDrivers(@Query("driver_number") driver_number: String?): List<DriverResponse>
}


//https://api.openf1.org/v1/sessions?year=2024&meeting_key=latest
//&date>=2024-03-24T05:24:15