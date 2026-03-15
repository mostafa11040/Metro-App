package com.example.metroapp.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.metroapp.domain.model.LineStations
import com.example.metroapp.ui.theme.TextSecondary

@Composable
fun TimelineItem(time: String, station: String, lineColor: Color, isStart: Boolean = false, isEnd: Boolean = false) {
    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
        Text(time, modifier = Modifier.width(60.dp), fontSize = 12.sp, color = TextSecondary)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(if (isStart || isEnd) lineColor else Color.White)
                    .then(if (!isStart && !isEnd) Modifier.background(Color.White).padding(2.dp).clip(CircleShape).background(lineColor) else Modifier)
            )
            if (!isEnd) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .weight(1f)
                        .background(lineColor)
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            station,
            fontWeight = if (isStart || isEnd || LineStations.isInterchange(station)) FontWeight.Bold else FontWeight.Normal,
            color = if (LineStations.isInterchange(station)) Color.Black else Color.Unspecified,
            modifier = Modifier.padding(bottom = 24.dp)
        )
    }
}