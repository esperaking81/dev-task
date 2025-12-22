package dev.espera.devtask.data.datasources.remote.models

import dev.espera.devtask.data.common.TaskPriority
import dev.espera.devtask.data.common.TaskStatus

data class Task(
    val assignee: User?,
    val assigneeId: String?,
    val createdAt: String,
    val description: String,
    val dueDate: String,
    val id: String,
    val order: Int,
    val parent: Task?,
    val parentId: String?,
    val priority: TaskPriority,
    val status: TaskStatus,
    val subtasks: List<Task> = emptyList(),
    val tags: List<Tag> = emptyList(),
    val title: String,
    val updatedAt: String,
    val userId: String
)