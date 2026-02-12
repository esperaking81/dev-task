package dev.espera.devtask.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.espera.devtask.ui.theme.AppTheme
import dev.espera.devtask.ui.theme.DeepNavy
import dev.espera.devtask.ui.theme.OnSurfaceVariant
import dev.espera.devtask.ui.theme.Outline
import dev.espera.devtask.ui.theme.Primary

@Composable
fun BreakdownWithAiCta(modifier: Modifier = Modifier) {
    val cornerShape = RoundedCornerShape(8.dp)
    Row(
        modifier = Modifier
            .background(brush = Brush.horizontalGradient(DeepNavy), shape = cornerShape)
            .border(width = 1.dp, shape = cornerShape, color = Outline)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.weight(1f),
        ) {
            Box(
                modifier = Modifier
                    .clip(cornerShape)
                    .background(Primary.copy(alpha = .1f))
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = "Ai Generated",
                    tint = Primary,
                )
            }
            Column(
                modifier = Modifier.padding(start = 12.dp),
            ) {
                Text(
                    text = "Break down with AI",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    "Generate actionable subtasks instantly",
                    color = OnSurfaceVariant,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
        Icon(
            Icons.Filled.ArrowForward,
            contentDescription = "Arrow forward",
        )
    }
}

@Preview
@Composable
fun BreakdownWithAiCta__Preview() {
    AppTheme { BreakdownWithAiCta() }
}