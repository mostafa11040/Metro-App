package com.example.metroapp.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.metroapp.R
import com.example.metroapp.ui.theme.MetroBlue

@Composable
fun MetroLogo(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(MetroBlue)
    ) {
        Image(
            painter = painterResource(id = R.drawable.metro),
            contentDescription = "Metro Logo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}