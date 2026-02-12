package dev.espera.devtask.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import devtask.composeapp.generated.resources.Res
import devtask.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

@Composable
fun DevTaskLogo() {
    val primaryColor = MaterialTheme.colorScheme.primary
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .background(primaryColor.copy(.1f), shape = MaterialTheme.shapes.small)
                .padding(8.dp),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                Icons.Default.Terminal,
                contentDescription = "Terminal",
                modifier = Modifier.size(24.dp),
                tint = primaryColor,
            )
        }
        Spacer(Modifier.width(8.dp))
        Text(stringResource(Res.string.app_name), fontWeight = FontWeight.Bold)
    }
}