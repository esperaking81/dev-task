package dev.espera.devtask.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.espera.devtask.ui.theme.AppTheme
import dev.espera.devtask.ui.theme.OnSurfaceVariant

@Composable
fun SubtaskCountProgressBar(
    modifier: Modifier = Modifier,
    isAiGenerated: Boolean = true,
    completedCount: Int = 0,
    totalCount: Int = 0,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = buildString {
                    append("Subtasks")
                    if (isAiGenerated) {
                        append(" ")
                        append("(AI Generated)")
                    }
                },
                color = OnSurfaceVariant,
                style = MaterialTheme.typography.bodySmall,
            )
            Text(text = buildString {
                append(completedCount)
                append("/")
                append(totalCount)
            }, color = MaterialTheme.colorScheme.primary)
        }

        LinearProgressIndicator(
            progress = {
                if (totalCount > 0) {
                    completedCount.toFloat() / totalCount.toFloat()
                } else {
                    .0f
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        SubtaskCountProgressBar(
            isAiGenerated = false,
            completedCount = 2,
            totalCount = 5,
        )
    }
}