package com.example.formulogy.data

import com.example.formulogy.network.ApiService
import com.example.formulogy.network.DriverResponse
import com.example.formulogy.network.MeetingResponse
import com.example.formulogy.network.PositionResponse
import com.example.formulogy.network.SessionResponse


interface F1Repository {
    suspend fun getSessions( meetingkey:String?): List<SessionResponse>
    suspend fun getPositions(): List<PositionResponse>
    suspend fun getMeetings(meetingkey:String?): List<MeetingResponse>
    suspend fun getDrivers(driver_number:String?): List<DriverResponse>
}

class F1RepositoryImpl(private val apiService: ApiService):F1Repository {
    override suspend fun getSessions( meetingkey:String?): List<SessionResponse> = apiService.getSessions(meetingkey)
    override suspend fun getPositions(): List<PositionResponse> = apiService.getPositions()
    override suspend fun getMeetings(meetingkey:String?): List<MeetingResponse> = apiService.getMeetings(meetingkey)
    override suspend fun getDrivers(driver_number:String?): List<DriverResponse> = apiService.getDrivers(driver_number)
}