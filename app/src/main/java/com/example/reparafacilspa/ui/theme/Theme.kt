package com.example.reparafacilspa.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Light = lightColorScheme(
    primary = Color(0xFF4F46E5),
    secondary = Color(0xFF7C3AED),
    tertiary = Color(0xFF22C55E),
    background = Color(0xFFF8F7FC),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onSecondary = Color.White,
)

@Composable
fun RFTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = Light, content = content)
}
