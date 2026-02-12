package dev.espera.devtask.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AvatarGroup(
    avatars: List<DrawableResource>,
    modifier: Modifier = Modifier,
    extraCount: Int = 0,
    avatarSize: Dp = 16.dp,
    overlapOffset: Dp = 20.dp,
) {
    Box(modifier = modifier) {
        avatars.forEachIndexed { index, avatar ->
            Image(
                painter = painterResource(avatar),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .offset(x = overlapOffset * index)
                    .size(avatarSize)
                    .zIndex(index.toFloat())
                    .clip(CircleShape)
                    .border(0.5.dp, Color.White, CircleShape)
            )
        }
        if (extraCount > 0) {
            Box(
                modifier = Modifier
                    .offset(x = overlapOffset * avatars.size)
                    .size(avatarSize)
                    .zIndex(avatars.size.toFloat())
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
                    .border(0.5.dp, Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "+$extraCount",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp)
                )
            }
        }
    }
}