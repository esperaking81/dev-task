package dev.espera.devtask.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.composeunstyled.UnstyledCheckbox
import dev.espera.devtask.ui.theme.Outline
import dev.espera.devtask.ui.theme.Primary
import dev.espera.devtask.ui.theme.SurfaceVariant

@Composable
fun Checkbox(
    checked: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit)? = null,
){
    UnstyledCheckbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        backgroundColor = if (checked) Primary else SurfaceVariant.copy(alpha = .5f),
        shape = RoundedCornerShape(6.dp),
        borderColor = Outline,
        modifier = Modifier.padding(16.dp).size(20.dp)
    ) {
        Icon(
            Icons.Rounded.Check,
            contentDescription = "Check icon",
        )
    }
}