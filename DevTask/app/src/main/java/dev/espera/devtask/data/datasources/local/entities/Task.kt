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
import dev.espera.devtask.data.common.TaskPriority
import dev.espera.devtask.data.common.TaskStatus

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "order") val order: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "parent_id") val parentId: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "due_date") val dueDate: String?,
    @ColumnInfo(name = "status") val status: TaskStatus,
    @ColumnInfo(name = "priority") val priority: TaskPriority,
    @ColumnInfo(name = "created_at") val createdAt: String,
    @ColumnInfo(name = "updated_at") val updatedAt: String
)

@Entity(
    primaryKeys = ["task_id", "tag_id"],
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["task_id"],
            onDelete = CASCADE,
        ),
        ForeignKey(
            entity = Tag::class,
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
    @Embedded val task: Task,

    @Relation(
        parentColumn = "user_id",
        entityColumn = "id",
        entity = User::class
    )
    val user: User,

    @Relation(
        parentColumn = "id",
        entityColumn = "parent_id",
    ) val subtasks: List<Task>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = TaskTagCrossRef::class,
            parentColumn = "task_id",
            entityColumn = "tag_id"
        )
    )
    val tags: List<Tag>
)
