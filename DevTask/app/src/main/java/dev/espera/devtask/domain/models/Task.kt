package dev.espera.devtask.domain.models

import dev.espera.devtask.data.common.TaskPriority
import dev.espera.devtask.data.common.TaskStatus
import java.time.LocalDateTime

data class Task(
    val id: String,
    val order: Int,
    val title: String,
    val description: String?,
    val dueDate: LocalDateTime,
    val status: TaskStatus,
    val priority: TaskPriority,
    val subtasks: List<Task>,
    val tags: List<Tag>,
    val user: User
)
