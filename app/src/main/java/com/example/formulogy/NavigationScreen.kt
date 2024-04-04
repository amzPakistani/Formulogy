package com.example.formulogy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.formulogy.ui.F1ViewModel
import com.example.formulogy.ui._uiState
import com.example.formulogy.ui.screens.HomeScreen
import com.example.formulogy.ui.screens.LiveScreen
import com.example.formulogy.ui.screens.TrackScreenLayout
import com.example.formulogy.ui.screens.TracksScreen

enum class Screen(val route: String) {

    HomeScreen("home_screen"),
    LiveScreen("live_screen"),
    TracksScreen("track_screen")
}



@Composable
fun NavigationScreen(uiState: _uiState, viewModel: F1ViewModel) {
    val navController = rememberNavController()
    Column() {
        Row(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 8.dp, top = 8.dp),
        ) {
            Button(
                onClick = { navController.navigate(Screen.HomeScreen.route) },
                modifier = Modifier.weight(1f)
            ) {
                Text("Sessions")
            }
            Spacer(modifier = Modifier.weight(.1f))
            Button(
                onClick = { navController.navigate(Screen.LiveScreen.route) },
                modifier = Modifier.weight(1f)
            ) {
                Text("Live")
            }
        }
        NavHost(navController = navController, startDestination = Screen.HomeScreen.route, modifier = Modifier.weight(1f)) {
            composable(Screen.HomeScreen.route) {
                HomeScreen(uiState = uiState, viewModel)
            }
            composable(Screen.LiveScreen.route) {
                LiveScreen(viewModel, navController)
            }
            composable(Screen.TracksScreen.route) {
                TracksScreen(viewModel = viewModel)
            }

        }
    }

}
