package com.example.metroapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = MetroBlue,
    secondary = OrangeLine2,
    tertiary = GreenLine3,
    background = TextPrimary,
    surface = TextPrimary,
    onPrimary = CardBackground,
    onSecondary = TextPrimary,
    onTertiary = CardBackground,
    onBackground = BackgroundColor,
    onSurface = BackgroundColor,
)

private val LightColorScheme = lightColorScheme(
    primary = MetroBlue,
    secondary = OrangeLine2,
    tertiary = GreenLine3,
    background = BackgroundColor,
    surface = CardBackground,
    onPrimary = CardBackground,
    onSecondary = TextPrimary,
    onTertiary = CardBackground,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
)

@Composable
fun MetroAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
