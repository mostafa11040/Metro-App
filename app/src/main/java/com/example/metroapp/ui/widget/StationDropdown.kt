package com.example.metroapp.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.metroapp.domain.model.LineStations
import com.example.metroapp.ui.theme.GreenLine3
import com.example.metroapp.ui.theme.OrangeLine2
import com.example.metroapp.ui.theme.RedLine1
import com.example.metroapp.ui.theme.TextPrimary
import com.example.metroapp.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StationDropdown(
    label: String,
    selectedStation: String,
    onStationSelected: (String) -> Unit,
    icon: ImageVector
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Icon(icon, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(label, style = MaterialTheme.typography.labelSmall, color = TextSecondary)
                Text(
                    text = if (selectedStation.isEmpty()) "Select a station..." else selectedStation,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (selectedStation.isEmpty()) Color.LightGray else TextPrimary
                )
            }
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.heightIn(max = 400.dp)
        ) {
            DropdownHeader("Line 1", RedLine1)
            LineStations.line1.forEach { station ->
                StationItem(station, onStationSelected) { expanded = false }
            }

            DropdownHeader("Line 2", OrangeLine2)
            LineStations.line2.forEach { station ->
                StationItem(station, onStationSelected) { expanded = false }
            }

            DropdownHeader("Line 3", GreenLine3)
            LineStations.line3.forEach { station ->
                StationItem(station, onStationSelected) { expanded = false }
            }
        }
    }
}