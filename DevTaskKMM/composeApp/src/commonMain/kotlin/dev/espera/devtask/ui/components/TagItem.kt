package dev.espera.devtask.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.espera.devtask.ui.theme.AppTheme
import dev.espera.devtask.ui.theme.ErrorOrHigh
import dev.espera.devtask.ui.theme.OnSurfaceVariant
import dev.espera.devtask.ui.theme.Outline
import dev.espera.devtask.ui.theme.SuccessOrLow
import dev.espera.devtask.ui.theme.SurfaceVariant

@Composable
fun TagItem(
    modifier: Modifier = Modifier,
    tag: String,
    isSelected: Boolean = false,
) {
    val backgroundColor = if (isSelected) Color.White else SurfaceVariant
    val foregroundColor = if (isSelected) Color.Black else OnSurfaceVariant
    val borderWidth = if (isSelected) 0.dp else 1.dp
    val borderShape = MaterialTheme.shapes.small
    val padding = PaddingValues(horizontal = 20.dp, vertical = 4.dp)

    Row(
        modifier = modifier
            .background(backgroundColor, shape = borderShape)
            .border(
                width = borderWidth,
                color = Outline,
                shape = borderShape,
            )
            .padding(padding),
    ) {
        Text(tag, color = foregroundColor)
    }
}

@Composable
fun TagList(modifier: Modifier = Modifier, selectedIndex: Int = -1, tags: List<String>) {
    val selectedTag = tags.getOrNull(selectedIndex) ?: ""
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        tags.forEach { tag ->
            TagItem(tag = tag, isSelected = tag == selectedTag)
        }
    }
}

@Composable
fun MetadataTag(
    modifier: Modifier = Modifier,
    tag: String,
    color: Color,
    shouldShowAiIcon: Boolean = false,
) {

    val lightColor = color.copy(alpha = 0.1f)
    val shape = MaterialTheme.shapes.small
    Row(
        modifier = modifier.background(lightColor, shape)
            .border(width = 0.dp, color = lightColor, shape = shape)
            .padding(vertical = 4.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (shouldShowAiIcon) {
            Icon(
                Icons.Default.AutoAwesome,
                contentDescription = "Artificial Intelligence magic",
                tint = color,
                modifier = Modifier.size(16.dp).padding(end = 4.dp),
            )
        }
        Text(
            color = color,
            letterSpacing = 1.sp,
            text = tag.uppercase(),
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
fun MetadataItem(
    modifier: Modifier = Modifier,
    text: String,
    icon: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier.background(SurfaceVariant).border(
            width = 0.5.dp,
            color = Outline,
            shape = MaterialTheme.shapes.small,
        ).padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon?.let {
            Box(
                modifier = Modifier.padding(end = 8.dp),
            ) {
                it()
            }
        }
        Text(text, style = MaterialTheme.typography.labelSmall.copy(color = White))
    }
}

@Preview
@Composable
fun MetadataTagPreview() {
    AppTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            MetadataTag(tag = "done", color = SuccessOrLow)
            MetadataTag(tag = "high priority", color = ErrorOrHigh)
            MetadataTag(tag = "ai breakdown", color = Cyan, shouldShowAiIcon = true)
        }
    }
}


@Preview
@Composable
fun TagPreview() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TagList(tags = listOf("All", "Frontend", "Backend"), selectedIndex = 0)
        }
    }
}

@Preview
@Composable
fun MetadataItem__Preview() {
    AppTheme {
        Column {
            MetadataItem(text = "Oct 24", icon = {
                Icon(
                    Icons.Default.CalendarMonth,
                    modifier = Modifier.size(16.dp),
                    contentDescription = "Calendar",
                    tint = Gray,
                )
            })
        }
    }
}