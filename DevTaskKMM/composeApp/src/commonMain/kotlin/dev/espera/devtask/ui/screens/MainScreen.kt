package dev.espera.devtask.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.espera.devtask.ui.components.BreakdownWithAiCta
import dev.espera.devtask.ui.components.DateComponent
import dev.espera.devtask.ui.components.SubtaskItem
import dev.espera.devtask.ui.components.TagList
import dev.espera.devtask.ui.components.TaskItem
import dev.espera.devtask.ui.theme.AppTheme
import dev.espera.devtask.ui.theme.ErrorOrHigh
import dev.espera.devtask.ui.theme.OnSurfaceVariant
import dev.espera.devtask.ui.theme.Outline
import dev.espera.devtask.ui.theme.Primary
import dev.espera.devtask.ui.theme.SuccessOrLow
import dev.espera.devtask.ui.utils.bottomBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onNavigateToDetails: () -> Unit) {
    val typography = MaterialTheme.typography
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Box(
                        modifier = Modifier.padding(start = 12.dp),
                    ) {
                        // Avatar
                        Box(
                            modifier = Modifier.size(36.dp).background(
                                color = Color(0xFFF8CFAF),
                                shape = CircleShape,
                            ),
                        )
                        // Online dot
                        Box(
                            modifier = Modifier.size(12.dp)
                                .background(color = SuccessOrLow, shape = CircleShape)
                                .border(width = 2.dp, color = Black, shape = CircleShape)
                                .align(Alignment.BottomEnd)
                        )
                    }
                },
                title = {
                    // Greetings
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        Text(
                            text = "Good Morning",
                            style = typography.labelSmall,
                            color = OnSurfaceVariant
                        )
                        Text(text = "Alex", style = typography.labelLarge)
                    }
                },
                actions = {
                    // Notifications
                    Box(
                        modifier = Modifier.padding(end = 12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                        )
                        // Online dot
                        Box(
                            modifier = Modifier.size(12.dp)
                                .background(color = Red, shape = CircleShape)
                                .border(width = 0.dp, color = Black, shape = CircleShape)
                                .align(Alignment.TopEnd)
                        )
                    }
                },
                modifier = Modifier.bottomBorder(
                    color = Outline,
                    height = .5f,
                )
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Progress Indicator
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text("4 Tasks", style = typography.titleLarge, color = White)
                        Text("75%", style = typography.titleMedium.copy(color = Primary))
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            "Pending for today", style = typography.labelSmall.copy(
                                color = OnSurfaceVariant,
                            )
                        )
                        Text(
                            "Daily Goal",
                            style = typography.labelSmall.copy(color = OnSurfaceVariant)
                        )
                    }
                    LinearProgressIndicator(
                        progress = { .75f },
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    )
                }

                // Calendar
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    // Date
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                    ) {
                        Text("February 2026", style = typography.titleMedium)
                        Text("Today", style = typography.labelLarge.copy(color = Primary))
                    }
                    // Date
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        DateComponent(
                            dayOfWeek = "Wed",
                            dayOfMonth = "11",
                            isSelected = true,
                            hasTasks = true,
                        )
                        DateComponent(
                            dayOfWeek = "Thu",
                            dayOfMonth = "12",
                            isSelected = false,
                            hasTasks = false,
                        )
                        DateComponent(
                            dayOfWeek = "Fri",
                            dayOfMonth = "13",
                            isSelected = false,
                            hasTasks = true,
                        )
                        DateComponent(
                            dayOfWeek = "Sat",
                            dayOfMonth = "14",
                            isSelected = false,
                            hasTasks = false,
                        )
                        DateComponent(
                            dayOfWeek = "Sun",
                            dayOfMonth = "15",
                            isSelected = false,
                            hasTasks = false,
                        )
                    }
                }

                // Filter
                TagList(
                    selectedIndex = 0,
                    modifier = Modifier.fillMaxWidth().padding(top = 24.dp)
                        .horizontalScroll(rememberScrollState()),
                    tags = listOf("All", "Frontend", "Backend", "Bugs", "DevOps"),
                )

                // Tasks
                LazyColumn(
                    modifier = Modifier.padding(top = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(4) {
                        TaskItem(isCompleted = false)
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun MainScreen__Preview() {
    AppTheme {
        MainScreen(onNavigateToDetails = {})
    }
}
