package com.example.reparafacilspa.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Colores principales de ReparaFácil
private val PrimaryBlue = Color(0xFF2563EB)
private val PrimaryBlueDark = Color(0xFF1E40AF)
private val SecondaryGreen = Color(0xFF10B981)
private val AccentOrange = Color(0xFFFF6B35)
private val BackgroundLight = Color(0xFFF8FAFC)
private val SurfaceWhite = Color(0xFFFFFFFF)
private val TextPrimary = Color(0xFF1E293B)
private val TextSecondary = Color(0xFF64748B)
private val ErrorRed = Color(0xFFEF4444)
private val SuccessGreen = Color(0xFF22C55E)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFDEEAFF),
    onPrimaryContainer = Color(0xFF001A41),

    secondary = SecondaryGreen,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD1FAE5),
    onSecondaryContainer = Color(0xFF022C22),

    tertiary = AccentOrange,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFE4D6),
    onTertiaryContainer = Color(0xFF3D0700),

    error = ErrorRed,
    errorContainer = Color(0xFFFFDAD6),
    onError = Color.White,
    onErrorContainer = Color(0xFF410002),

    background = BackgroundLight,
    onBackground = TextPrimary,

    surface = SurfaceWhite,
    onSurface = TextPrimary,
    surfaceVariant = Color(0xFFE2E8F0),
    onSurfaceVariant = TextSecondary,

    outline = Color(0xFFCBD5E1),
    outlineVariant = Color(0xFFE2E8F0),
)

@Composable
fun ReparaFacilTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}

@Composable
fun RFTheme(content: @Composable () -> Unit) {
    ReparaFacilTheme(content = content)
}