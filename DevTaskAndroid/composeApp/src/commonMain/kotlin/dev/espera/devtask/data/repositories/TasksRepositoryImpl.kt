package dev.espera.devtask.data.repositories

import dev.espera.devtask.data.datasources.local.entities.toDomain
import dev.espera.devtask.data.datasources.remote.sources.TasksLocalDataSource
import dev.espera.devtask.data.datasources.remote.sources.TasksRemoteDataSource
import dev.espera.devtask.domain.models.Task
import dev.espera.devtask.domain.repositories.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TasksRepositoryImpl(
    val tasksRemoteDataSource: TasksRemoteDataSource,
    val tasksLocalDataSource: TasksLocalDataSource,
) : TasksRepository {
    override suspend fun getTasks(): Flow<List<Task>> = tasksLocalDataSource.getAllTasks()
        .map { taskEntities -> taskEntities.map { it.toDomain() } }
}
