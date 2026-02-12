package dev.espera.devtask.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.espera.devtask.ui.theme.AppTheme
import dev.espera.devtask.ui.theme.OnSurfaceVariant

@Composable
fun StatusToggleBar(modifier: Modifier = Modifier, currentStatus: String) {
    Row(
        modifier = modifier.fillMaxWidth()
            .background(OnSurfaceVariant.copy(.1f), shape = MaterialTheme.shapes.small)
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        listOf("To Do", "In Progress", "Done").map { status ->
            StatusButton(status = status, isActive = status == currentStatus)
        }
    }
}

@Composable
fun StatusButton(status: String, isActive: Boolean, modifier: Modifier = Modifier) {
    val buttonColor = if (isActive) OnSurfaceVariant.copy(.3f) else Transparent
    val textColor = if (isActive) White else OnSurfaceVariant.copy(.5f)

    Box(
        modifier = modifier
            .background(buttonColor, shape = MaterialTheme.shapes.extraSmall)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(status, color = textColor, style = MaterialTheme.typography.labelMedium)
    }
}

@Preview
@Composable
fun StatusToggleBar__Preview() {
    AppTheme {
        StatusToggleBar(
            currentStatus = "In Progress",
        )
    }
}