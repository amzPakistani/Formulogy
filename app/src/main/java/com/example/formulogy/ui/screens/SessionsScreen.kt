package com.example.formulogy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formulogy.network.SessionResponse

@Composable
fun SessionScreen(data: List<SessionResponse>) {
    val groupedData = data.groupBy { it.location }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(start = 8.dp, end = 8.dp, bottom = 8.dp)) {
        groupedData.forEach { (location, sessions) ->
            item {
                SessionList(itemLocation = location, sessions = sessions)
            }
        }
    }
}

@Composable
fun SessionList(itemLocation: String, sessions: List<SessionResponse>) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = itemLocation, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(12.dp))
            Divider()

            sessions.forEach { session ->
                SessionItem(item = session)
            }
        }
    }
}

@Composable
fun SessionItem(item:SessionResponse, modifier: Modifier= Modifier) {
    Card(
        modifier = modifier.fillMaxWidth().padding(8.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Text(text = "${item.location}: ${item.session_name}", style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = item.date_start.substringBefore("T"))
        }
    }
}