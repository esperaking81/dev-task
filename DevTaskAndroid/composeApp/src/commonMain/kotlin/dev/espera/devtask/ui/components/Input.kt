package dev.espera.devtask.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.espera.devtask.R
import dev.espera.devtask.ui.theme.BorderDark
import dev.espera.devtask.ui.theme.Gray
import dev.espera.devtask.ui.theme.BrightBlue
import dev.espera.devtask.ui.theme.CardBackground
import dev.espera.devtask.ui.theme.DevTaskTheme

@Composable
fun DevTaskInput(
    label: String,
    value: String,
    placeholder: String = "",
    onValueChange: (String) -> Unit,
    leadingIcon: ImageVector? = null,
    isPassword: Boolean = false
) {
    var isPasswordVisible by remember { mutableStateOf(true) }

    Column {
        Text(text = label, style = MaterialTheme.typography.bodyMedium.copy(color = Color.White))
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardBackground, RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = BorderDark,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 16.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isPassword) {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = null, tint = Gray)
                } else {
                    if (leadingIcon != null) {
                        Icon(imageVector = leadingIcon, contentDescription = null, tint = Gray)
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.weight(1f),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                    visualTransformation = if (isPassword && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                    cursorBrush = SolidColor(BrightBlue),
                    singleLine = true
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = MaterialTheme.typography.bodyMedium.copy(color = Gray)
                        )
                    }
                    it()
                }
                if (isPassword) {
                    val icon = if (isPasswordVisible) R.drawable.ic_visibility_off
                    else R.drawable.ic_visibility_on
                    Icon(
                        modifier = Modifier.clickable {
                            isPasswordVisible = !isPasswordVisible
                        },
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        tint = Gray
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000,
)
@Composable
fun DevTaskInputPreview() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    DevTaskTheme {
        Column {
            DevTaskInput(
                value = email,
                label = "Email",
                onValueChange = { email = it },
                placeholder = "dev@devtask.com",
                leadingIcon = Icons.Default.Email,
            )
            DevTaskInput(
                value = password,
                isPassword = true,
                label = "Password",
                onValueChange = { password = it },
            )
        }
    }
}