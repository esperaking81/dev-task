package dev.espera.devtask.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.espera.devtask.ui.theme.Outline
import dev.espera.devtask.ui.theme.Primary
import dev.espera.devtask.ui.theme.SurfaceVariant

@Composable
fun CustomTabs(
    onTabSelected: (Int) -> Unit,
    selectedTabIndex: Int,
    tabs: List<String>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceVariant)
            .border(1.dp, Outline, RoundedCornerShape(12.dp))
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { index, text ->
                val isSelected = selectedTabIndex == index
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isSelected) Primary else Transparent)
                        .clickable { onTabSelected(index) }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = text,
                        style  = MaterialTheme.typography.labelMedium.copy(
                            color = if (isSelected) Color.White else Color.Gray,
                            fontWeight = FontWeight.SemiBold,
                        )
                    )
                }
            }
        }
    }
}