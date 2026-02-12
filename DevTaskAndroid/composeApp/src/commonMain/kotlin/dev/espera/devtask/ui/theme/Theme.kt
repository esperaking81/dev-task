package dev.espera.devtask.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import Typography
import androidx.compose.ui.graphics.Color

val DevTaskDarkColorScheme = darkColorScheme(
    // Core brand / accent color — the glowing neon cyan/blue used in arcs, button, logo
    primary = Color(0xFF00D4FF),           // vivid cyan-blue (close to electric neon)
    onPrimary = Color(0xFF00141F),         // very dark for text/icons on primary

    // Usually a bit toned down from primary for secondary elements
    secondary = Color(0xFF40C4FF),         // lighter cyan variant
    onSecondary = Color.Black,

    // Tertiary can be a complementary or neutral accent (optional subtle purple/blue)
    tertiary = Color(0xFF80DEEA),          // soft cyan-teal
    onTertiary = Color(0xFF004D40),

    // Background surface — very dark (almost black) with subtle blue tint
    background = Color(0xFF0A0E17),        // deep dark navy-black
    onBackground = Color(0xFFE0F7FA),      // very light cyan-white for text

    // Main surface color for cards, sheets, inputs (slightly lighter than background)
    surface = Color(0xFF11171F),           // dark gray-blue
    onSurface = Color(0xFFE3F2FD),         // light blue-gray text

    // Variants for elevation / containers
    surfaceVariant = Color(0xFF1E293B),    // slightly lighter slate
    onSurfaceVariant = Color(0xFFB3E5FC),

    // Used for disabled states, outlines, subtle dividers
    outline = Color(0xFF4FC3F7),
    outlineVariant = Color(0xFF0288D1),

    // Error / warning states (keep standard or customize if needed)
    error = Color(0xFFCF6679),
    onError = Color.Black,

    // Additional M3 colors often used
    scrim = Color.Black.copy(alpha = 0.5f),
    inverseSurface = Color(0xFFE0F7FA),
    inverseOnSurface = Color(0xFF00363A),
    inversePrimary = Color(0xFF006064),

    // Surface tones for elevation (optional — M3 generates these automatically in many cases)
    surfaceBright = Color(0xFF1C252E),
    surfaceDim = Color(0xFF0A0E17),
)

@Composable
fun DevTaskTheme(
    darkTheme: Boolean = true, // Force dark theme
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Disable dynamic color
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
