package com.example.formulogy.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.formulogy.R
import com.example.formulogy.network.WeatherResponse
import com.example.formulogy.ui.F1ViewModel

@Composable
fun WeatherScreen(F1ViewModel: F1ViewModel){
    val weatherResponseList = F1ViewModel.weather.collectAsState().value
    val latestWeather = weatherResponseList?.last()

    LaunchedEffect(Unit) {
        F1ViewModel.getWeather()
    }

    if (latestWeather != null) {
        WeatherItem(weatherResponse = latestWeather)
    }
}

@Composable
fun WeatherItem(weatherResponse: WeatherResponse, modifer: Modifier = Modifier){
    Column(
        modifier = modifer
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp, top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Weather", style = MaterialTheme.typography.headlineLarge)
        val imagePainterCloud = painterResource(id = R.drawable.cloud)
        val imagePainterSun = painterResource(id = R.drawable.sunny)
        if (weatherResponse.rainfall==1) {
            Image(painter = imagePainterCloud, contentDescription = "My Image")
        }else{
            Image(painter = imagePainterSun, contentDescription = "My Image")
        }
        LazyColumn {
            item { WeatherItem("Track Temperature", "${weatherResponse.track_temperature}°C") }
            item { Divider() }
            item { WeatherItem("Air Temperature", "${weatherResponse.air_temperature}°C") }
            item { Divider() }
            item { WeatherItem("Wind", "${weatherResponse.wind_speed} m/s") }
            item { Divider() }
            item { WeatherItem("Rainfall", "${weatherResponse.rainfall}") }
            item { Divider() }
        }
    }
}


@Composable
fun WeatherItem(title: String, text: String, modifer: Modifier = Modifier) {
    Row(
        modifier = modifer
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 12.dp,
                bottom = 12.dp
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${title}:",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 22.sp,
            modifier = Modifier.weight(2f)
        )
    }
}