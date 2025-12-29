package dev.espera.devtask.data.datasources.remote.sources

import dev.espera.devtask.data.datasources.remote.models.TagApiModel
import dev.espera.devtask.data.datasources.remote.network.DevTaskApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface TagRemoteDataSource {
    suspend fun fetchTags(): List<TagApiModel>
}

class TagNetworkDataSource(
    val devTaskApi: DevTaskApi,
    val ioDispatcher: CoroutineDispatcher,
) : TagRemoteDataSource {
    override suspend fun fetchTags(): List<TagApiModel> {
        return withContext(ioDispatcher){
            devTaskApi.fetchTags()
        }
    }
}