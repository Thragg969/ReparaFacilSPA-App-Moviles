package com.example.reparafacilspa.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun ReparaFacilTheme(content: @Composable () -> Unit) {
    // Colores por defecto (luz). Si quieres, personaliza luego.
    val colors = lightColorScheme()

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
