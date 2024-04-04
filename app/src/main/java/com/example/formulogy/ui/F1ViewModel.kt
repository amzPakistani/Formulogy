package com.example.formulogy.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.formulogy.F1Application
import com.example.formulogy.data.F1Repository
import com.example.formulogy.data.F1RepositoryImpl
import com.example.formulogy.network.DriverResponse
import com.example.formulogy.network.MeetingResponse
import com.example.formulogy.network.PositionResponse
import com.example.formulogy.network.SessionResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed interface _uiState{
    data class Success(val session:List<SessionResponse>, val meeting: List<MeetingResponse>):_uiState
    object Error:_uiState
    object Loading:_uiState
}

class F1ViewModel(private val f1Repository: F1Repository):ViewModel(){
    var uiState:_uiState by mutableStateOf(_uiState.Loading)
        private set

    private val _sessions = MutableStateFlow<List<SessionResponse>?>(null)
    private val _meetings = MutableStateFlow<List<MeetingResponse>?>(null)

    // Expose sessions and meetings as StateFlow
    val sessions: StateFlow<List<SessionResponse>?> = _sessions
    val meetings: StateFlow<List<MeetingResponse>?> = _meetings

    private var fetchJob: Job? = null
    // Initialize positions and drivers as MutableStateFlow
    private val _positions = MutableStateFlow<List<PositionResponse>?>(null)
    private val _drivers = MutableStateFlow<List<DriverResponse>?>(null)

    // Expose positions and drivers as StateFlow
    val positions: StateFlow<List<PositionResponse>?> = _positions
    val drivers: StateFlow<List<DriverResponse>?> = _drivers


    init {
        getSessionsAndMeetings(null)
        getDrivers(null)

    }

    fun getSessionsAndMeetings( meetingkey:String?){
        viewModelScope.launch {
            uiState = _uiState.Loading
            try {
                val sessions = f1Repository.getSessions(meetingkey)
                val meetings = f1Repository.getMeetings(meetingkey)
                _sessions.value = sessions
                _meetings.value = meetings
                uiState = _uiState.Success(sessions, meetings)
            } catch (e: UnknownHostException) {
                uiState = _uiState.Error
                Log.e("F1ViewModel", "Unknown host exception fetching sessions and meetings", e)
            } catch (e: SocketTimeoutException) {
                uiState = _uiState.Error
                Log.e("F1ViewModel", "Socket timeout exception fetching sessions and meetings", e)
            } catch (e: IOException) {
                uiState = _uiState.Error
                Log.e("F1ViewModel", "IO Error fetching sessions and meetings", e)
            } catch (e: HttpException) {
                uiState = _uiState.Error
                Log.e("F1ViewModel", "HTTP Error fetching sessions and meetings", e)
            }
        }
    }

    fun getDrivers(driver_number: String?) {
        viewModelScope.launch {
            try {
                val newdrivers = f1Repository.getDrivers(driver_number)
                _drivers.value = newdrivers

            } catch (e: UnknownHostException) {
                uiState = _uiState.Error
                Log.e("F1ViewModel", "Unknown host exception getDrivers", e)
            } catch (e: SocketTimeoutException) {
                uiState = _uiState.Error
                Log.e("F1ViewModel", "Socket timeout exception getDrivers", e)
            } catch (e: IOException) {
                uiState = _uiState.Error
                Log.e("F1ViewModel", "IO Error getDrivers", e)
            } catch (e: HttpException) {
                uiState = _uiState.Error
                Log.e("F1ViewModel", "HTTP Error getDrivers", e)
            }
        }
    }

    fun startFetchingPositions(){
        fetchJob = viewModelScope.launch {
            var oldPositions: List<PositionResponse>? = null
            while(true){
                try {
                    val newPositions = f1Repository.getPositions()
                    if (newPositions == oldPositions) {
                        // If the positions have not changed, delay the next fetch by 2 minutes
                        kotlinx.coroutines.delay(120000)
                    } else {
                        // If the positions have changed, update the positions and delay the next fetch by 30 seconds
                        _positions.value = newPositions
                        kotlinx.coroutines.delay(30000)
                    }
                    oldPositions = newPositions
                    Log.i("F1ViewModel", "Positions fetched")

                } catch (e: UnknownHostException) {
                    uiState = _uiState.Error
                    Log.e("F1ViewModel", "Unknown host exception FetchingPositions", e)
                } catch (e: SocketTimeoutException) {
                    uiState = _uiState.Error
                    Log.e("F1ViewModel", "Socket timeout exception FetchingPositions", e)
                } catch (e: IOException) {
                    uiState = _uiState.Error
                    Log.e("F1ViewModel", "IO Error FetchingPositions", e)
                } catch (e: HttpException) {
                    uiState = _uiState.Error
                    Log.e("F1ViewModel", "HTTP Error FetchingPositions", e)
                }
            }
        }
    }

    fun stopFetchingPositions(){
        fetchJob?.cancel()
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as F1Application
                val repository = app.container.f1Repository
                F1ViewModel(repository)
            }
        }
    }
}