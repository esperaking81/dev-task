package dev.espera.devtask.data.datasources.remote.models

import dev.espera.devtask.common.domain.model.TaskPriority
import dev.espera.devtask.common.domain.model.TaskStatus
import dev.espera.devtask.data.datasources.converters.DateTimeConverters
import dev.espera.devtask.data.datasources.local.entities.TaskEntity
import kotlinx.serialization.Serializable

val dateTimeConverters: DateTimeConverters = DateTimeConverters()

@Serializable
data class TaskApiModel(
    val assignees: List<UserApiModel> = emptyList(),
    val subtasks: List<TaskApiModel> = emptyList(),
    val tags: List<TagApiModel> = emptyList(),
    val description: String?,
    val createdAt: String,
    val dueDate: String,
    val id: String,
    val order: Int,
    val parent: TaskApiModel?,
    val parentId: String?,
    val priority: TaskPriority,
    val status: TaskStatus,
    val title: String,
    val updatedAt: String,
    val userId: String
)

fun TaskApiModel.toEntity(taskApiModel: TaskApiModel): TaskEntity {
    return TaskEntity(
        id = taskApiModel.id,
        title = taskApiModel.title,
        order = taskApiModel.order,
        status = taskApiModel.status,
        parentId = taskApiModel.parentId,
        priority = taskApiModel.priority,
        description = taskApiModel.description,
        dueDate = dateTimeConverters.fromIsoString(taskApiModel.dueDate),
        createdAt = dateTimeConverters.fromIsoString(taskApiModel.createdAt),
        updatedAt = dateTimeConverters.fromIsoString(taskApiModel.updatedAt),
    )
}