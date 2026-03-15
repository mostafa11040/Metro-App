package com.example.metroapp.ui.Screens

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.metroapp.domain.model.LineStations
import com.example.metroapp.ui.theme.BackgroundColor
import com.example.metroapp.ui.theme.CardBackground
import com.example.metroapp.ui.theme.GreenLine3
import com.example.metroapp.ui.theme.MetroBlue
import com.example.metroapp.ui.theme.OrangeLine2
import com.example.metroapp.ui.theme.RedLine1
import com.example.metroapp.ui.theme.TextSecondary
import com.example.metroapp.ui.widget.MetroLogo
import kotlinx.coroutines.delay

@Composable
fun ArrivalScreen(tripStations: List<String>, onBack: () -> Unit) {
    var currentStationIndex by remember { mutableIntStateOf(0) }

    val currentStation = tripStations.getOrNull(currentStationIndex) ?: ""
    val isCurrentInterchange = LineStations.isInterchange(currentStation)

    val currentLineColor = remember(currentStation) {
        when {
            LineStations.line1.contains(currentStation) -> RedLine1
            LineStations.line2.contains(currentStation) -> OrangeLine2
            LineStations.line3.contains(currentStation) -> GreenLine3
            else -> OrangeLine2
        }
    }

    LaunchedEffect(Unit) {
        while (currentStationIndex < tripStations.size - 1) {
            delay(3000)
            currentStationIndex++
        }
    }

    val animatedProgress by animateFloatAsState(
        targetValue = currentStationIndex.toFloat() / (tripStations.size - 1).coerceAtLeast(1),
        animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing),
        label = "TrainProgress"
    )

    Box(modifier = Modifier.fillMaxSize().background(BackgroundColor)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            drawLine(RedLine1, start = Offset(0f, height * 0.35f), end = Offset(width, height * 0.35f), strokeWidth = 10f)
            drawLine(OrangeLine2, start = Offset(0f, height * 0.45f), end = Offset(width, height * 0.45f), strokeWidth = 10f)
            drawLine(GreenLine3, start = Offset(0f, height * 0.55f), end = Offset(width, height * 0.55f), strokeWidth = 10f)

            val lineY = when {
                LineStations.line1.contains(currentStation) -> height * 0.35f
                LineStations.line2.contains(currentStation) -> height * 0.45f
                LineStations.line3.contains(currentStation) -> height * 0.55f
                else -> height * 0.45f
            }

            val trainX = width * 0.1f + (width * 0.8f * animatedProgress)

            drawCircle(Color.White, radius = 15f, center = Offset(trainX, lineY))
            // تلوين النقطة بالأسود إذا كانت في محطة تبادلية
            drawCircle(if (isCurrentInterchange) Color.Black else currentLineColor, radius = 10f, center = Offset(trainX, lineY))
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = CardBackground),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            val isArrived = currentStationIndex == tripStations.size - 1
                            Text(
                                text = if (isArrived) "Arrived at:" else "Now approaching:",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextSecondary
                            )
                            Text(
                                text = "$currentStation Station",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (isCurrentInterchange) Color.Black else Color.Unspecified
                            )
                        }
                        MetroLogo()
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    val nextStation = tripStations.getOrNull(currentStationIndex + 1)
                    if (nextStation != null) {
                        Text("Next stop: $nextStation", style = MaterialTheme.typography.bodyMedium, color = if (LineStations.isInterchange(nextStation)) Color.Black else MetroBlue)
                    } else {
                        Text("You have reached your destination.", style = MaterialTheme.typography.bodyMedium, color = GreenLine3, fontWeight = FontWeight.Bold)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            val isFinished = currentStationIndex == tripStations.size - 1
            Button(
                onClick = { if (isFinished) onBack() },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = if (isFinished) GreenLine3 else (if (isCurrentInterchange) Color.Black else currentLineColor)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = if (isFinished) "FINISH TRIP" else "TRIP IN PROGRESS...", fontWeight = FontWeight.Bold)
            }
        }
        IconButton(onClick = onBack, modifier = Modifier.padding(24.dp).background(Color.White, CircleShape).align(Alignment.TopStart)) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
        }
    }
}