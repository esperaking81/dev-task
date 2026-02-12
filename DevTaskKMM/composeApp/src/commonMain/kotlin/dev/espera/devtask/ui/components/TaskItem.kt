package dev.espera.devtask.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.espera.devtask.ui.theme.AppTheme
import dev.espera.devtask.ui.theme.ErrorOrHigh
import dev.espera.devtask.ui.theme.OnSurfaceVariant
import dev.espera.devtask.ui.theme.Outline
import dev.espera.devtask.ui.theme.SurfaceVariant
import devtask.composeapp.generated.resources.Res
import devtask.composeapp.generated.resources.avatar_1
import devtask.composeapp.generated.resources.avatar_2

@Composable
fun TaskItem(modifier: Modifier = Modifier, isCompleted: Boolean = false) {
    Card(
        modifier = modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
            containerColor = SurfaceVariant,
        ),
        border = BorderStroke(1.dp, Outline.copy(alpha = .3f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    MetadataTag(tag = "high priority", color = ErrorOrHigh)
                    MetadataTag(tag = "ai breakdown", color = Cyan, shouldShowAiIcon = true)
                }
                Spacer(Modifier.weight(1f))
                if (isCompleted) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = "More options",
                        tint = Gray,
                    )
                } else {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.MoreHoriz,
                            contentDescription = "More options",
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                val textDecoration =
                    if (isCompleted) TextDecoration.LineThrough else TextDecoration.None
                Text(
                    "Refactor Auth API", style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = .75.sp,
                        textDecoration = textDecoration
                    )
                )
                Text(
                    "Optimize token refresh logic and improve performance",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = OnSurfaceVariant
                    ),
                )
            }

            AnimatedVisibility(
                visible = !isCompleted,
                modifier = Modifier.padding(top = 24.dp),
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        DueTime(
                            icon = Icons.Default.Schedule,
                            time = "Due Today, 12:00 PM",
                            tint = ErrorOrHigh
                        )
                        AvatarGroup(
                            modifier = Modifier.padding(end = 10.dp),
                            avatars = listOf(Res.drawable.avatar_1, Res.drawable.avatar_2),
                            overlapOffset = 10.dp,
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 16.dp)
                    )

                    SubtaskCountProgressBar(
                        isAiGenerated = false,
                        completedCount = 3,
                        totalCount = 5,
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TaskItem__Preview() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TaskItem()
            TaskItem(isCompleted = true)
        }
    }
}