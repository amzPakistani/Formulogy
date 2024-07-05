package com.example.formulogy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.formulogy.network.WeatherResponse
import com.example.formulogy.ui.F1ViewModel

@Composable
fun WeatherScreen(F1ViewModel: F1ViewModel){
    val weatherResponse = F1ViewModel.weather.collectAsState().value
    WeatherItem(weatherResponse = weatherResponse)
}

@Composable
fun WeatherItem(weatherResponse: WeatherResponse){
    Column {
        Text(text = weatherResponse.date)
        Text(text = weatherResponse.rainfall.toString())
        Text(text = weatherResponse.track_temperature.toString())
    }
}