package dev.espera.devtask.data.datasources.local.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import dev.espera.devtask.common.domain.model.TaskPriority
import dev.espera.devtask.common.domain.model.TaskStatus
import dev.espera.devtask.domain.models.Task
import java.time.LocalDateTime

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "order") val order: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "status") val status: TaskStatus,
    @ColumnInfo(name = "parent_id") val parentId: String?,
    @ColumnInfo(name = "due_date") val dueDate: LocalDateTime,
    @ColumnInfo(name = "priority") val priority: TaskPriority,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "created_at") val createdAt: LocalDateTime,
    @ColumnInfo(name = "updated_at") val updatedAt: LocalDateTime
)

fun TaskEntity.toDomain(): Task = Task(
    id = id,
    order = order,
    title = title,
    status = status,
    dueDate = dueDate,
    tags = emptyList(),
    priority = priority,
    updatedAt = updatedAt,
    createdAt = createdAt,
    assignees = emptyList(),
    description = description,
)

@Entity(
    tableName = "task_assignees",
    primaryKeys = ["task_id", "assignee_id"],
    foreignKeys = [
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = ["id"],
            childColumns = ["task_id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["assignee_id"],
            onDelete = CASCADE
        )
    ],
    indices = [Index(value = ["task_id"]), Index(value = ["assignee_id"])]
)
data class TaskAssigneeCrossRef(
    @ColumnInfo("task_id") val taskId: String,
    @ColumnInfo("assignee_id") val assigneeId: String
)

@Entity(
    tableName = "task_tags",
    primaryKeys = ["task_id", "tag_id"],
    foreignKeys = [
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = ["id"],
            childColumns = ["task_id"],
            onDelete = CASCADE,
        ),
        ForeignKey(
            entity = TagEntity::class,
            parentColumns = ["id"],
            childColumns = ["tag_id"],
            onDelete = CASCADE
        )
    ],
    indices = [Index(value = ["task_id"]), Index(value = ["tag_id"])]
)
data class TaskTagCrossRef(
    @ColumnInfo("task_id") val taskId: String,
    @ColumnInfo("tag_id") val tagId: String
)

data class TaskWithRelations(
    @Embedded val task: TaskEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = TaskAssigneeCrossRef::class,
            parentColumn = "task_id",
            entityColumn = "assignee_id"
        )
    )
    val assignees: List<UserEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = TaskTagCrossRef::class,
            parentColumn = "task_id",
            entityColumn = "tag_id"
        )
    )
    val tags: List<TagEntity>
)

fun TaskWithRelations.toDomain(): Task = Task(
    id = task.id,
    order = task.order,
    title = task.title,
    status = task.status,
    dueDate = task.dueDate,
    priority = task.priority,
    updatedAt = task.updatedAt,
    createdAt = task.createdAt,
    description = task.description,
    tags = tags.map { it.toDomain() },
    assignees = assignees.map { it.toDomain() },
)
