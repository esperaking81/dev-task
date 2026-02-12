package dev.espera.devtask.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.unit.dp

val DevTaskDarkColorScheme = darkColorScheme(
    // Core brand / accent color — the glowing neon cyan/blue used in arcs, button, logo
    primary = Primary,           // vivid cyan-blue (close to electric neon)
    onPrimary = White,         // very dark for text/icons on primary

    // Usually a bit toned down from primary for secondary elements
    secondary = Color(0xFF40C4FF),         // lighter cyan variant
    onSecondary = Color.Black,

    // Tertiary can be a complementary or neutral accent (optional subtle purple/blue)
    tertiary = Color(0xFF80DEEA),          // soft cyan-teal
    onTertiary = Color(0xFF004D40),

    // Background surface — very dark (almost black) with subtle blue tint
    background = Color(0xFF101622),        // deep dark navy-black
    onBackground = Color(0xFFE0F7FA),      // very light cyan-white for text

    // Main surface color for cards, sheets, inputs (slightly lighter than background)
    surface = Color(0xFF1E232E),           // dark gray-blue
    onSurface = White,         // light blue-gray text

    // Variants for elevation / containers
    surfaceVariant = Color(0xFF1D2939),    // slightly lighter slate
    onSurfaceVariant = Color(0xFF98A2B3),

    // Used for disabled states, outlines, subtle dividers
    outline = Outline,
    // outlineVariant = Color(0xFF0288D1),

    // Error / warning states (keep standard or customize if needed)
    error = ErrorOrHigh,

    // Additional M3 colors often used
    scrim = Color.Black.copy(alpha = 0.5f),

    // Surface tones for elevation (optional — M3 generates these automatically in many cases)
    surfaceBright = Color(0xFF1C252E),
    surfaceDim = Color(0xFF0A0E17),
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DevTaskDarkColorScheme,
        typography = appTypography(),
        content = {
            Surface(
                content = content,
                modifier = Modifier.background(brush = Brush.linearGradient(DeepNavy))
            )
        }
    )
}
