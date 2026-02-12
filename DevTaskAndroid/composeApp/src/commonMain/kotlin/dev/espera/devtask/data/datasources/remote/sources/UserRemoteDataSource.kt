package dev.espera.devtask.data.datasources.remote.sources

import dev.espera.devtask.data.datasources.remote.models.LoginDto
import dev.espera.devtask.data.datasources.remote.models.LoginResponse
import dev.espera.devtask.data.datasources.remote.network.DevTaskApi

interface UsersRemoteDataSource {
    suspend fun login(dto: LoginDto): LoginResponse
}

class UsersNetworkDataSource(
    val devTaskApi: DevTaskApi,
) : UsersRemoteDataSource {
    override suspend fun login(dto: LoginDto): LoginResponse {
        return devTaskApi.login(dto)
    }
}