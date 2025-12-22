package dev.espera.devtask.domain.models

import dev.espera.devtask.data.common.TaskPriority
import dev.espera.devtask.data.common.TaskStatus

data class Task(
    val id: String,
    val title: String,
    val description: String?,
    val dueDate: String?,
    val status: TaskStatus,
    val priority: TaskPriority,
)
