package dev.espera.devtask.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import devtask.composeapp.generated.resources.Res
import devtask.composeapp.generated.resources.plus_jakarta_sans_bold
import devtask.composeapp.generated.resources.plus_jakarta_sans_medium
import devtask.composeapp.generated.resources.plus_jakarta_sans_regular
import devtask.composeapp.generated.resources.plus_jakarta_sans_semi_bold
import devtask.composeapp.generated.resources.space_grotesk_bold
import devtask.composeapp.generated.resources.space_grotesk_medium
import devtask.composeapp.generated.resources.space_grotesk_regular


@Composable
fun appTypography(): androidx.compose.material3.Typography {
    val baseTypography = MaterialTheme.typography
    val spaceGrotesk = FontFamily(
        Font(Res.font.space_grotesk_regular, FontWeight.Normal),
        Font(Res.font.space_grotesk_medium, FontWeight.Medium),
        Font(Res.font.space_grotesk_bold, FontWeight.Bold)
    )
    val plusJakartaSans = FontFamily(
        Font(Res.font.plus_jakarta_sans_regular),
        Font(Res.font.plus_jakarta_sans_medium, FontWeight.Medium),
        Font(Res.font.plus_jakarta_sans_bold, FontWeight.Bold),
        Font(Res.font.plus_jakarta_sans_semi_bold, FontWeight.SemiBold)
    )
    return androidx.compose.material3.Typography(
        bodyLarge = baseTypography.bodyLarge.copy(
            fontFamily = plusJakartaSans,
        ),
        bodyMedium = baseTypography.bodyLarge.copy(
            fontFamily = plusJakartaSans,
        ),
        bodySmall = baseTypography.bodyMedium.copy(
            fontFamily = plusJakartaSans,
        ),
        titleLarge = baseTypography.titleLarge.copy(
            fontFamily = spaceGrotesk,
        ),
        titleMedium = baseTypography.titleMedium.copy(
            fontFamily = spaceGrotesk,
        ),
        titleSmall = baseTypography.titleSmall.copy(
            fontFamily = spaceGrotesk,
        ),
        labelLarge = baseTypography.labelLarge.copy(
            fontFamily = plusJakartaSans,
        ),
        labelMedium = baseTypography.labelMedium.copy(
            fontFamily = plusJakartaSans,
        ),
        labelSmall = baseTypography.labelSmall.copy(
            fontFamily = plusJakartaSans,
        ),
        headlineLarge = baseTypography.headlineLarge.copy(
            fontFamily = spaceGrotesk,
        )
    )
}