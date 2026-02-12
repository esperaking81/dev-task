package dev.espera.devtask.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ScheduleSend
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarViewWeek
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.twotone.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.espera.devtask.ui.theme.AppTheme
import dev.espera.devtask.ui.theme.ErrorOrHigh

@Composable
fun DueTime(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    time: String,
    tint: Color,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            tint = tint,
            imageVector = icon,
            contentDescription = "Schedule icon",
            modifier = Modifier.size(16.dp),
        )
        Text(
            text = time,
            color = tint,
            modifier = Modifier.padding(start = 4.dp),
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Preview
@Composable
fun DueTimePreview() {
    AppTheme {
        Column {
            DueTime(
                icon = Icons.Filled.CalendarMonth,
                time = "Due Tomorrow",
                tint = Gray,
            )
            DueTime(
                icon = Icons.Filled.Schedule,
                time = "Due Today, 12:00 PM",
                tint = ErrorOrHigh,
            )
        }
    }
}