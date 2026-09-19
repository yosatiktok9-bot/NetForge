package com.netforge.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DuskColors = darkColorScheme(
    primary = Accent,
    onPrimary = Color.White,
    secondary = Ember,
    background = Ink,
    onBackground = Chalk,
    surface = Paper,
    onSurface = Chalk,
    surfaceVariant = Paper2,
    onSurfaceVariant = Slate,
    error = Rust,
    tertiary = Moss
)

@Composable
fun NetForgeTheme(dawn: Boolean = false, content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = DuskColors, content = content)
}
