package dev.espera.devtask.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.espera.devtask.ui.theme.BrightBlue

@Composable
fun DevTaskButton(
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean = true,
) {
    val backgroundColor = if (isEnabled) BrightBlue else Color.Gray.copy(.5f)
    val textColor = if (isEnabled) Color.White else Color.Gray

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background( backgroundColor)
            .clickable {
                if (isEnabled){
                    onClick()
                }
            }
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                color = textColor
            )
        )
    }
}
