package com.example.metroapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.metroapp.ui.MetroViewModel
import com.example.metroapp.ui.Screens.HomeScreen
import com.example.metroapp.ui.Screens.TripDetailScreen
import com.example.metroapp.ui.Screens.ArrivalScreen
import com.example.metroapp.ui.theme.MetroAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MetroAppTheme {

                val viewModel: MetroViewModel = viewModel()
                var currentScreen by remember { mutableIntStateOf(0) }
                var startStation by remember { mutableStateOf("") }
                var endStation by remember { mutableStateOf("") }
                
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        when (currentScreen) {
                            0 -> HomeScreen(
                                recentTrips = viewModel.recentTrips,
                                onNavigateToDetail = { start, end ->
                                    startStation = start
                                    endStation = end
                                    viewModel.addTrip(start, end)
                                    currentScreen = 1
                                }
                            )
                            1 -> {
                                val tripStations = viewModel.getTripStations(startStation, endStation)
                                TripDetailScreen(
                                    tripStations = tripStations,
                                    endStation = endStation,
                                    onBack = { currentScreen = 0 },
                                    onNavigateToArrival = { currentScreen = 2 }
                                )
                            }
                            2 -> {
                                val tripStations = viewModel.getTripStations(startStation, endStation)
                                ArrivalScreen(
                                    tripStations = tripStations,
                                    onBack = { currentScreen = 1 }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
