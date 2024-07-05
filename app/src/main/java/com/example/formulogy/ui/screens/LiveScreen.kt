package com.example.formulogy.ui.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.example.formulogy.network.DriverResponse
import com.example.formulogy.network.PositionResponse
import com.example.formulogy.ui.F1ViewModel
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.formulogy.Screen
import com.example.formulogy.network.MeetingResponse
import com.example.formulogy.network.SessionResponse

@Composable
fun LiveScreen(viewModel: F1ViewModel, navController: NavController) {

    val isStarted = remember { mutableStateOf(false) }

    if (!isStarted.value) {
        LaunchedEffect(key1 = Unit) {
            viewModel.startFetchingPositions()
            viewModel.getDrivers(null)
            viewModel.getSessionsAndMeetings("latest")
        }
        isStarted.value = true
    }

    val meetings = viewModel.meetings.collectAsState(initial = null)
    val sessions = viewModel.sessions.collectAsState(initial = null)
    val positions = viewModel.positions.collectAsState(initial = null)
    val drivers = viewModel.drivers.collectAsState(initial = null)
    if (positions.value != null && drivers.value != null) {

        val driverMap = drivers.value!!.associateBy { it.driver_number.toString() }

        val positionMap = positions.value!!.associateBy { it.position }

        Log.i("LiveScreen", "driverMap: $driverMap")
        Log.i("LiveScreen", "positionMap: $positionMap")

        LiveList(positionMap, driverMap, meetings.value!![0], sessions.value!!.last(), navController = navController)
    }
}

@Composable
fun LiveList(
    positionMap: Map<Int, PositionResponse>,
    driverMap: Map<String, DriverResponse>,
    meetingResponse: MeetingResponse,
    sessionResponse: SessionResponse,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp, bottom = 16.dp, top = 16.dp),
        verticalArrangement = Arrangement.Center, // Center vertically
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.navigate(Screen.WeatherScreen.route) }, modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(0.25f)
            ) {
                Icon(
                    Icons.Filled.MoreVert,
                    contentDescription = "Expand",
                    modifier = Modifier.size(200.dp)
                )
            }
            Text(
                text = meetingResponse.meeting_name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.weight(1.5f)
            )
            IconButton(
                onClick = { navController.navigate(Screen.TracksScreen.route) }, modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(0.25f)
            ) {
                Icon(
                    Icons.Filled.MoreVert,
                    contentDescription = "Expand",
                    modifier = Modifier.size(200.dp)
                )
            }
        }
        ElevatedCard(
            modifier = Modifier
                .padding()
                .fillMaxSize()
                .padding(start = 12.dp, end = 12.dp, bottom = 12.dp, top = 20.dp),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = sessionResponse.session_name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 20.sp,
                    letterSpacing = 0.7.sp,
                    modifier = Modifier
                        .padding(top = 12.dp, bottom = 16.dp)

                )
                LazyColumn() {
                    items(positionMap.values.toList().sortedBy { it.position }) { position ->
                        val driver = driverMap[position.driver_number.toString()]
                        if (driver != null) {
                            LiveItem(position, driver)
                        }
                    }
                }
            }

        }
    }
}


//modifier = Modifier.padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)

@Composable
fun LiveItem(
    position: PositionResponse,
    driver: DriverResponse,
    modifier: Modifier = Modifier
) {

    var expanded by remember {
        mutableStateOf(false)
    }
    val teamColor = android.graphics.Color.parseColor("#${driver.team_colour}")
    val SauberColor = android.graphics.Color.parseColor("#82D950")
    val VCARColor = android.graphics.Color.parseColor("#0033CC")

    var gradient = Brush.horizontalGradient(
        colors = listOf(
            Color(teamColor),
            Color.Black,
            Color.Black
        )
    )

    if (driver.team_colour == "C92D4B") {
        gradient = Brush.horizontalGradient(
            colors = listOf(
                Color(SauberColor),
                Color.Black,
                Color.Black
            )
        )
    } else if (driver.team_colour == "5E8FAA") {
        gradient = Brush.horizontalGradient(
            colors = listOf(
                Color(VCARColor),
                Color.Black,
                Color.Black
            )
        )
    }

    val teamName =
        if (driver.team_name == "Alfa Romeo") "Kick Sauber" else if (driver.team_name == "AlphaTauri") "VCA RB" else driver.team_name
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(brush = gradient)
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .width(30.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = position.position.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .width(50.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = driver.name_acronym, style = MaterialTheme.typography.titleLarge)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = teamName ?: "", style = MaterialTheme.typography.titleLarge)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                if (expanded) {
                    expanded = false
                } else {
                    expanded = true
                }
            }
            ) {
                if (expanded) {
                    Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Expand")
                } else {
                    Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Collapse")
                }
            }
        }
        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(), // animation when the content appears
            exit = shrinkVertically() // animation when the content disappears
        ) {
            DriverDetail(driver = driver, modifier = modifier.padding(8.dp))
        }
    }

    Divider()
    Log.i("LiveScreen", "Displaying driver: ${driver.full_name}, position: ${position.position}")
    Log.i("LiveScreen", "Position of driver ${driver.full_name}: ${position.position}")
}

@Composable
fun DriverDetail(
    driver: DriverResponse,
    modifier: Modifier = Modifier
) {
    var gradient = Brush.horizontalGradient(
        colors = listOf(
            Color(255, 251, 251),
            Color.White,
            Color(255, 250, 250)
        )
    )
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(260, 260, 260),
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            modifier = Modifier.background(brush = gradient),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = driver.headshot_url,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp, start = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Driver: ${driver.full_name}",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(1.5.dp)
                )
                Text(
                    text = "Number: ${driver.driver_number}",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(1.5.dp)
                )
                Text(
                    text = "Country: ${driver.country_code}",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(1.5.dp)
                )
            }
        }
    }
}


//@Composable
//fun LiveItem(
//
//    position: PositionResponse,
//    modifier: Modifier = Modifier
//) {
//
//
//    Row() {
//        Column {
//            Text(text = "Position")
//            Text(text = position.position.toString())
//        }
//        Column {
//            Text(text = "Driver")
//            Text(text = position.driver_number.toString())
//        }
//
//    }
//}