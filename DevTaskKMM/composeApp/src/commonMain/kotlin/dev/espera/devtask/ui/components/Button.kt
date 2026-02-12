package dev.espera.devtask.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.espera.devtask.ui.theme.OnSurfaceVariant

enum class ButtonVariant {
    Primary,
    Secondary,
    Ghost,
    Link
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun Button(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit = {},
    icon: (@Composable () -> Unit)? = null,
    variant: ButtonVariant = ButtonVariant.Primary
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val typography = MaterialTheme.typography

    val containerColor = when (variant) {
        ButtonVariant.Primary -> primaryColor
        else -> Transparent
    }

    val contentColor = when (variant) {
        ButtonVariant.Primary -> White
        ButtonVariant.Secondary -> primaryColor
        else -> OnSurfaceVariant
    }

    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        shapes = ButtonDefaults.shapes(
            shape = RoundedCornerShape(8.dp),
        ),
    ) {
        icon?.let {
            Box(modifier = Modifier.padding(end = 8.dp)) {
                icon()
            }
        }
        Text(label, style = typography.titleMedium, fontWeight = FontWeight.Bold)
    }
}