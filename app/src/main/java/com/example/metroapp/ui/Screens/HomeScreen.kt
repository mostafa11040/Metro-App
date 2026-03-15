package com.example.metroapp.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.metroapp.R
import com.example.metroapp.domain.model.Trip
import com.example.metroapp.ui.theme.*
import com.example.metroapp.ui.widget.MetroLogo
import com.example.metroapp.ui.widget.RecentTripCard
import com.example.metroapp.ui.widget.StationDropdown




@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HomeScreen(recentTrips: List<Trip>, onNavigateToDetail: (String, String) -> Unit) {
    var leavingFrom by remember { mutableStateOf("") }
    var headingTo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "SATURDAY ☀️",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextSecondary
                )
                Text(
                    text = "14 March, 2026",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
            MetroLogo()
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Where do you want to\ngo for today?",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                lineHeight = 40.sp
            ),
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardBackground),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                StationDropdown(
                    label = "I'm leaving from:",
                    selectedStation = leavingFrom,
                    onStationSelected = { leavingFrom = it },
                    icon = Icons.Default.LocationOn
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), color = BackgroundColor)

                StationDropdown(
                    label = "I'm heading to:",
                    selectedStation = headingTo,
                    onStationSelected = { headingTo = it },
                    icon = Icons.Default.Place
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { onNavigateToDetail(leavingFrom, headingTo) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = OrangeLine2),
                    shape = RoundedCornerShape(16.dp),
                    enabled = leavingFrom.isNotEmpty() && headingTo.isNotEmpty() && leavingFrom != headingTo
                ) {
                    Text("SEARCH", fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (recentTrips.isNotEmpty()) {
            Text("Recent Trips", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                recentTrips.take(3).forEach { trip ->
                    RecentTripCard(trip, onClick = { onNavigateToDetail(trip.from, trip.to) })
                }
            }
        }
    }
}














