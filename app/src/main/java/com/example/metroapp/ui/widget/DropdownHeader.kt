package com.example.metroapp.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DropdownHeader(title: String, color: Color) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .background(color.copy(alpha = 0.1f))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        style = MaterialTheme.typography.labelLarge,
        color = color,
        fontWeight = FontWeight.Bold
    )
}
