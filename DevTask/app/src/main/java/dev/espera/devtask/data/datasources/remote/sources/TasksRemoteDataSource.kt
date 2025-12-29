package dev.espera.devtask.data.datasources.remote.sources

import dev.espera.devtask.data.datasources.remote.models.TaskApiModel
import dev.espera.devtask.data.datasources.remote.network.DevTaskApi

interface TasksRemoteDataSource {
    suspend fun getTasks(): List<TaskApiModel>
}

class TaskNetworkDataSource(
    val devTaskApi: DevTaskApi,
) : TasksRemoteDataSource {
    override suspend fun getTasks(): List<TaskApiModel> {
        return devTaskApi.fetchTasks()
    }
}