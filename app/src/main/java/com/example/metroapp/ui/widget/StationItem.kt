package com.example.metroapp.ui.widget

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.metroapp.domain.model.LineStations

@Composable
fun StationItem(station: String, onSelect: (String) -> Unit, onDismiss: () -> Unit) {
    DropdownMenuItem(
        text = {
            Text(
                text = station,
                color = if (LineStations.isInterchange(station)) Color.Black else Color.Unspecified,
                fontWeight = if (LineStations.isInterchange(station)) FontWeight.Bold else FontWeight.Normal
            )
        },
        onClick = {
            onSelect(station)
            onDismiss()
        }
    )
}