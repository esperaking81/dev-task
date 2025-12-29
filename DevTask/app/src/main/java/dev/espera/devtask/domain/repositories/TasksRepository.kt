package dev.espera.devtask.domain.repositories

import dev.espera.devtask.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    suspend fun getTasks(): Flow<List<Task>>
}