package com.example.metroapp.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.metroapp.domain.model.LineStations
import com.example.metroapp.ui.theme.BackgroundColor
import com.example.metroapp.ui.theme.GreenLine3
import com.example.metroapp.ui.theme.MetroBlue
import com.example.metroapp.ui.theme.OrangeLine2
import com.example.metroapp.ui.theme.RedLine1
import com.example.metroapp.ui.theme.TextSecondary
import com.example.metroapp.ui.widget.TimelineItem

@Composable
fun TripDetailScreen(tripStations: List<String>, endStation: String, onBack: () -> Unit, onNavigateToArrival: () -> Unit) {
    val firstStation = tripStations.firstOrNull() ?: ""
    val initialLineColor = remember(firstStation) {
        when {
            LineStations.line1.contains(firstStation) -> RedLine1
            LineStations.line2.contains(firstStation) -> OrangeLine2
            LineStations.line3.contains(firstStation) -> GreenLine3
            else -> OrangeLine2
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(24.dp)
    ) {
        IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Your Trip to $endStation",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text("Total ${tripStations.size - 1} stops", color = TextSecondary)

        Spacer(modifier = Modifier.height(32.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            itemsIndexed(tripStations) { index, station ->
                val currentStationLineColor = when {
                    LineStations.isInterchange(station) -> Color.Black
                    LineStations.line1.contains(station) -> RedLine1
                    LineStations.line2.contains(station) -> OrangeLine2
                    LineStations.line3.contains(station) -> GreenLine3
                    else -> initialLineColor
                }
                TimelineItem(
                    time = "Stop ${index + 1}",
                    station = station,
                    lineColor = currentStationLineColor,
                    isStart = index == 0,
                    isEnd = index == tripStations.size - 1
                )
            }
        }

        Button(
            onClick = onNavigateToArrival,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MetroBlue),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Track Trip", fontWeight = FontWeight.Bold)
        }
    }
}