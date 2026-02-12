package dev.espera.devtask.data.datasources.remote.sources

import dev.espera.devtask.data.datasources.local.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TasksLocalDataSource {
    fun getAllTasks(): Flow<List<TaskEntity>>

    suspend fun insertTasks(with: List<TaskEntity>)
}