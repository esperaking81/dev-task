package dev.espera.devtask.data.datasources.remote.network

import dev.espera.devtask.data.datasources.remote.models.LoginDto
import dev.espera.devtask.data.datasources.remote.models.LoginResponse
import dev.espera.devtask.data.datasources.remote.models.TagApiModel
import dev.espera.devtask.data.datasources.remote.models.TaskApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class DevTaskApiImpl(
    private val client: HttpClient,
) : DevTaskApi {
    companion object {
        private const val BASE_URL = "http://localhost:3000"
    }

    override suspend fun login(
        with: LoginDto
    ): LoginResponse {
        return client.post("$BASE_URL/login") {
            setBody(with)
        }.body<LoginResponse>()
    }

    override suspend fun fetchTasks(): List<TaskApiModel> {
        return client.get("$BASE_URL/tasks").body<List<TaskApiModel>>()
    }

    override suspend fun fetchTags(): List<TagApiModel> {
        return client.get("$BASE_URL/tags").body<List<TagApiModel>>()
    }
}

interface DevTaskApi {
    // authentication
    suspend fun login(with: LoginDto): LoginResponse

    // tasks
    suspend fun fetchTasks(): List<TaskApiModel>

    // tags
    suspend fun fetchTags(): List<TagApiModel>
}