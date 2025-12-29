package dev.espera.devtask.data.datasources.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import androidx.room.OnConflictStrategy.Companion.REPLACE
import dev.espera.devtask.data.datasources.local.entities.TaskEntity
import dev.espera.devtask.data.datasources.local.entities.TaskTagCrossRef
import dev.espera.devtask.data.datasources.local.entities.TaskWithRelations
import dev.espera.devtask.data.datasources.local.entities.TaskAssigneeCrossRef

@Dao
interface TaskDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertAll(vararg tasks: TaskEntity)

    @Delete
    suspend fun delete(task: TaskEntity)

    // Tag
    @Insert(onConflict = REPLACE)
    suspend fun insertTaskTag(crossRef: TaskTagCrossRef)

    @Query("DELETE FROM task_tags WHERE task_id = :taskId")
    suspend fun deleteTaskTags(taskId: String)

    @Transaction
    suspend fun updateTaskTags(taskId: String, tags: List<String>){
        deleteTaskTags(taskId)
        tags.forEach { tagId ->
            insertTaskTag(TaskTagCrossRef(taskId, tagId))
        }
    }

    // Assignee
    @Insert(onConflict = REPLACE)
    suspend fun insertTaskAssignee(crossRef: TaskAssigneeCrossRef)

    @Query("DELETE FROM task_assignees WHERE task_id = :taskId")
    suspend fun deleteTaskAssignees(taskId: String)

    @Transaction
    suspend fun updateTaskAssignees(taskId: String, assignees: List<String>){
        deleteTaskAssignees(taskId)
        assignees.forEach { assigneeId ->
            insertTaskAssignee(TaskAssigneeCrossRef(taskId, assigneeId))
        }
    }

    @Transaction
    @Query("SELECT * FROM tasks")
    fun getAllWithRelations(): Flow<List<TaskWithRelations>>

    @Transaction
    @Query("SELECT * FROM tasks where parent_id = :taskId")
    fun getSubtasksFor(taskId: String): Flow<List<TaskWithRelations>>

    @Transaction
    @Query("SELECT * FROM tasks where id = :taskId")
    fun getTaskWithRelations(taskId: String): Flow<List<TaskWithRelations>>
}