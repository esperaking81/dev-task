package dev.espera.devtask.data.datasources.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import dev.espera.devtask.data.datasources.local.entities.Task
import dev.espera.devtask.data.datasources.local.entities.TaskWithRelations
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertAll(vararg tasks: Task)

    @Delete
    suspend fun delete(task: Task)

    @Insert
    fun insertTasksAndSubtasks(task: Task, subtasks: List<Task>)

    @Query("SELECT * FROM tasks")
    fun getAll(): Flow<List<TaskWithRelations>>

    @Transaction
    @Query("SELECT * FROM tasks where id = :taskId")
    fun getTaskWithRelations(taskId: String): Flow<List<TaskWithRelations>>
}