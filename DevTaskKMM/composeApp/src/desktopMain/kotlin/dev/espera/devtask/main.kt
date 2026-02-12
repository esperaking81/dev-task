package dev.espera.devtask

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "DevTask",
        alwaysOnTop = true,
        state = WindowState(
            width = 393.dp,
            height = 800.dp
        )
    ) {
        App()
    }
}