package dev.espera.devtask.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.espera.devtask.ui.theme.AppTheme

@Composable
fun SubtaskItem(modifier: Modifier = Modifier, task: String = "Task", isCompleted: Boolean) {
    var checked by remember { mutableStateOf(isCompleted) }
    Row(
        modifier = modifier.fillMaxWidth()
            .background(Black, shape = RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(checked = checked, onCheckedChange = {
            checked = !checked
        })
        Text(
            text = task,
            maxLines = 1,
            modifier = Modifier.weight(1f),
            overflow = TextOverflow.Ellipsis,
            textDecoration = if (checked) TextDecoration.LineThrough else TextDecoration.None,
            color = if (checked) White.copy(alpha = .5f) else White,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Preview
@Composable
fun SubtaskItem__Preview() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SubtaskItem(
                task = "Create Google Cloud Console Project",
                isCompleted = true
            )
            SubtaskItem(
                task = "Install passport.js and strategies",
                isCompleted = false
            )
            SubtaskItem(
                task = "Configure callback routes",
                isCompleted = false
            )
        }
    }

}