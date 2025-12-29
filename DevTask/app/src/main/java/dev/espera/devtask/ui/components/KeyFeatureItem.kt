package dev.espera.devtask.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo
import dev.espera.devtask.R
import dev.espera.devtask.ui.theme.BrightBlue
import dev.espera.devtask.ui.theme.CardBackground
import dev.espera.devtask.ui.theme.DevTaskTheme
import dev.espera.devtask.ui.theme.Gray

@Composable
fun KeyFeatureItem(
    image: Painter,
    title: String,
    description: String,
    icon: Painter
) {
    Card(
        modifier = Modifier
            .width(260.dp)
            .padding(start = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xff1A2230))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(painter = icon, contentDescription = null, tint = BrightBlue)
                Text(
                    text = title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                color = Gray,
                fontSize = 12.sp,
                lineHeight = 16.sp,
            )
        }
    }
}

@Preview(
    backgroundColor = 0xFF101622,
)
@Composable
fun KeyFeatureItemPreview() {
    DevTaskTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            LazyRow(
                modifier = Modifier.padding(innerPadding)
            ) {
                items(3) {
                    KeyFeatureItem(
                        image = painterResource(id = R.drawable.ai_breakdown),
                        title = "AI Breakdown",
                        description = "Paste your user story, get a generated checklist instantly.",
                        icon = painterResource(id = R.drawable.ic_smart_toy)
                    )
                }
            }
        }
    }
}
