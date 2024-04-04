package com.example.formulogy.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.formulogy.R
import com.example.formulogy.ui.F1ViewModel
import com.example.formulogy.ui._uiState

@Composable
fun HomeScreen(uiState:_uiState, viewModel: F1ViewModel) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getSessionsAndMeetings(null) // Fetch meetings and sessions without a specific meeting key
    }

    when(uiState){
        is _uiState.Success -> SessionScreen(uiState.session)
        is _uiState.Loading -> LoadingScreen()
        is _uiState.Error -> ErrorScreen()
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.fillMaxSize(),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = null
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = "Failed To Load :/", modifier = Modifier.padding(16.dp))
    }
}