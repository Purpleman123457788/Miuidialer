package com.example.miuidialer.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val MiuiBlue = Color(0xFF0C8CE9)
val MiuiBackgroundLight = Color(0xFFF7F7F7)
val MiuiSurfaceLight = Color(0xFFFFFFFF)
val MiuiTextPrimary = Color(0xFF111111)

val LightColorScheme = lightColorScheme(
    primary = MiuiBlue,
    background = MiuiBackgroundLight,
    surface = MiuiSurfaceLight,
    onPrimary = Color.White,
    onBackground = MiuiTextPrimary,
    onSurface = MiuiTextPrimary
)

@Composable
fun MiuiDialerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}
