package dev.espera.devtask.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.espera.devtask.ui.theme.OnSurfaceVariant
import dev.espera.devtask.ui.theme.SuccessOrLow

@Composable
fun BreakdownSuggestionItem(
    task: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(checked = false, onCheckedChange = {})
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = task, maxLines = 1, overflow = TextOverflow.Ellipsis, color = White,
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(
                Modifier.height(4.dp)
            )
            Text(
                "Low Difficulty",
                color = SuccessOrLow,
                style = MaterialTheme.typography.labelSmall
            )
        }
        IconButton(
            onClick = {},
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Edit",
                tint = OnSurfaceVariant,
            )
        }
    }
}

@Preview
@Composable
fun BreakdownSuggestionItem__Preview() {
    BreakdownSuggestionItem(task = "Create Google Cloud Console Project")
}