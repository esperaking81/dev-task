package dev.espera.devtask.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.espera.devtask.ui.theme.OnSurfaceVariant
import dev.espera.devtask.ui.theme.Outline
import dev.espera.devtask.ui.theme.SurfaceVariant
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun KeyFeature(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    cover: DrawableResource,
    label: String,
    description: String,
    contentDescription: String? = null,
) {
    val typography = MaterialTheme.typography
    val primaryColor = MaterialTheme.colorScheme.primary

    Card(
        modifier = modifier,
        border = BorderStroke(
            width = 1.dp,
            color = Outline,
        ),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceVariant,
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Box(modifier = Modifier.width(260.dp)) {
                Image(
                    contentScale = ContentScale.Crop,
                    painter = painterResource(cover),
                    contentDescription = contentDescription,
                    modifier = Modifier.fillMaxWidth().clip(MaterialTheme.shapes.medium)
                        .aspectRatio(16f / 9),
                )
            }

            Row(modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)) {
                Icon(
                    icon,
                    contentDescription = "Feature icon",
                    tint = primaryColor,
                )
                Text(
                    label,
                    modifier = Modifier.padding(start = 8.dp),
                    style = typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
            Text(
                description,
                style = typography.bodyMedium.copy(
                    color = OnSurfaceVariant,
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}