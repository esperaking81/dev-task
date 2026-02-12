package dev.espera.devtask.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonShapes
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.espera.devtask.ui.theme.AppTheme
import dev.espera.devtask.ui.theme.Outline
import dev.espera.devtask.ui.theme.Primary
import dev.espera.devtask.ui.theme.SurfaceVariant

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DateComponent(
    dayOfWeek: String,
    dayOfMonth: String,
    isSelected: Boolean,
    hasTasks: Boolean,
    onClick: () -> Unit = {},
) {
    Button(
        contentPadding = PaddingValues(8.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Primary else SurfaceVariant,
            contentColor = White,
        ),
        border = BorderStroke(if (isSelected) 0.dp else 1.dp, Outline),
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = dayOfWeek,
                fontWeight = FontWeight.Medium,
                color = White.copy(alpha = .8f),
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = dayOfMonth,
                color = White,
                style = MaterialTheme.typography.titleLarge,
            )
            if (hasTasks) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) Color.White else Primary)
                )
            } else {
                Box(modifier = Modifier.size(6.dp)) // To maintain layout consistency
            }
        }
    }
}

@Preview
@Composable
fun DateComponentPreview() {
    AppTheme {
        DateComponent(dayOfWeek = "Mon", dayOfMonth = "12", isSelected = true, hasTasks = true)
    }
}

@Preview
@Composable
fun DateComponentUnselectedPreview() {
    AppTheme {
        DateComponent(dayOfWeek = "Wed", dayOfMonth = "14", isSelected = false, hasTasks = true)
    }
}

@Preview
@Composable
fun DateComponentUnselectedNoTasksPreview() {
    AppTheme {
        DateComponent(dayOfWeek = "Tue", dayOfMonth = "13", isSelected = false, hasTasks = false)
    }
}