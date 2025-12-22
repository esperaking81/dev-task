package dev.espera.devtask.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.espera.devtask.ui.theme.BrightBlue
import dev.espera.devtask.ui.theme.CardBackground
import dev.espera.devtask.ui.theme.DevTaskTheme
import dev.espera.devtask.ui.theme.TextColor

@Composable
fun DateComponent(
    dayOfWeek: String,
    dayOfMonth: String,
    isSelected: Boolean,
    hasTasks: Boolean
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) BrightBlue else CardBackground
        ),
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = dayOfWeek,
                style = MaterialTheme.typography.labelSmall,
                color = TextColor
            )
            Text(
                text = dayOfMonth,
                style = MaterialTheme.typography.titleLarge,
                color = TextColor
            )
            if (hasTasks) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) Color.White else BrightBlue)
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
    DevTaskTheme {
        DateComponent(dayOfWeek = "Mon", dayOfMonth = "12", isSelected = true, hasTasks = true)
    }
}

@Preview
@Composable
fun DateComponentUnselectedPreview() {
    DevTaskTheme {
        DateComponent(dayOfWeek = "Wed", dayOfMonth = "14", isSelected = false, hasTasks = true)
    }
}

@Preview
@Composable
fun DateComponentUnselectedNoTasksPreview() {
    DevTaskTheme {
        DateComponent(dayOfWeek = "Tue", dayOfMonth = "13", isSelected = false, hasTasks = false)
    }
}