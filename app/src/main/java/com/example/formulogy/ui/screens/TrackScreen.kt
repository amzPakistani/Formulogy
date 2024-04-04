package com.example.formulogy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.formulogy.data.TrackRep
import com.example.formulogy.network.MeetingResponse
import com.example.formulogy.ui.F1ViewModel

@Composable
fun TracksScreen(viewModel: F1ViewModel) {
    val isStarted = remember { mutableStateOf(false) }

    if (!isStarted.value) {
        LaunchedEffect(key1 = Unit) {
            viewModel.getSessionsAndMeetings("latest")
        }
        isStarted.value = true
    }
    val fetchedMeeting = viewModel.meetings.collectAsState(initial = null)

    if (fetchedMeeting.value != null) {
        TrackScreenLayout(fetchedMeeting.value?.get(0)!!)
    }
}

@Composable
fun TrackScreenLayout(meetingResponse: MeetingResponse, modifier: Modifier = Modifier) {
    val track = TrackRep.tracks.find { it.name == meetingResponse.meeting_name }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp, top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (track != null) {
            Text(text = track.name, style = MaterialTheme.typography.headlineLarge)
            AsyncImage(
                model = track.image,
                contentDescription = "Track Image",
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 24.dp, bottom = 24.dp)
                    .fillMaxWidth()
            )
            LazyColumn {
                item { TrackItem(title = "Location", text = track.location) }
                item { Divider() }
                item { TrackItem(title = "Length", text = track.length) }
                item { Divider() }
                item { TrackItem(title = "Corners", text = track.corners) }
                item { Divider() }
                item { TrackItem(title = "Laps", text = track.laps) }
                item { Divider() }
                item { SpecialTrackItem(title = "Record", text = track.lap_record, driverName = track.driver) }
                item { Divider() }
                item { TrackItem(title = "First GP", text = track.startedIn) }
            }
        }
    }


}

@Composable
fun TrackItem(title: String, text: String, modifer: Modifier = Modifier) {
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
            modifier = Modifier.weight(1f) // This will allocate a fixed proportion of the available space to the first Text
        )
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 22.sp,
            modifier = Modifier.weight(2f) // This will allocate a fixed proportion of the available space to the second Text
        )
    }
}
@Composable
fun SpecialTrackItem(title: String, text: String,driverName:String, modifer: Modifier = Modifier) {
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
            modifier = Modifier.weight(1f) // This will allocate a fixed proportion of the available space to the first Text
        )
        Column(modifier = Modifier.weight(2f)) {
            Text(
                text = text,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 22.sp,
            )
            Text(text = driverName, style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(top = 4.dp) )
        }

    }
}