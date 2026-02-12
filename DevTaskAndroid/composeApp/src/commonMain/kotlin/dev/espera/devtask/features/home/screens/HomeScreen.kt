package dev.espera.devtask.features.home.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data object HomeScreen

@Composable
fun HomeScreen(){
    Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            Text("Home screen")
        }
    }
}