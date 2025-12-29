package dev.espera.devtask.domain.models

import dev.espera.devtask.common.domain.model.TaskPriority
import dev.espera.devtask.common.domain.model.TaskStatus
import java.time.LocalDateTime

data class Task(
    val id: String,
    val order: Int,
    val title: String,
    val tags: List<Tag>,
    val status: TaskStatus,
    val description: String?,
    val assignees: List<User>,
    val priority: TaskPriority,
    val dueDate: LocalDateTime,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)